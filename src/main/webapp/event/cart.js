var cartvue = new Vue({
    el: '#page-content',
    data: {
        user:{},
        type:'cart',
        //购物车
        carts: [],
        sum: 0,
        recadd : "",
        freight: 20,
        //订单
        orders:[]
    },
    created: function () {
        var that = this;
        let user = JSON.parse(sessionStorage.getItem("user"));
        if (user == null) {
            $.confirm({
                title: 'LogIn first',
                content: 'go to login',
                theme: 'supervan',
                buttons: {
                    "login": function () {
                        $(window).attr('location', 'account.html');
                    },
                    "back": function () {
                        $(window).attr('location', 'index.html');
                    }
                }
            });
            return false;
        }
        console.log("The current user information: ");
        console.log(user);
        this.user = user;

        that.$nextTick(() => {
            that.getorders();
            that.getcarts();
            that.recadd = user.address;
        });
    },
    methods: {
        //购物车页面
        select: function (index) {
            this.carts[index].selected = !this.carts[index].selected;
            this.getsum();
        },
        getsum: function () {
            var price = 0;
            var carts = this.carts;
            for (var i = 0; i < carts.length; i++) {
                if (carts[i].selected) {
                    price += (carts[i].amount) * (carts[i].price);
                }
            }
            this.sum = price;
        },
        remove: function (cartid) {
            var that = this;
            let data = new URLSearchParams();
            data.append('cartID', cartid);
            axios({
                url: './deletecart',
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            })
                .then(function (response) {
                    console.log("deletecart服务器响应数据：");
                    console.log(response.data.flag);
                    if (response.data.flag) {
                        $.alert({
                            backgroundDismiss: true,
                            title: '',
                            content: 'Remove Successfully'
                        });
                        that.getcarts();
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        toremove: function (cartid) {
            $.confirm({
                theme: 'modern',
                title: '',
                content: 'Sure to remove this?',
                buttons: {
                    Yes: function () {
                        cartvue.remove(cartid);
                    },
                    cancel: function () {
                    }
                }
            });
        },
        getcarts: function () {
            var that = this;
            let user = JSON.parse(sessionStorage.getItem("user"));
            let data = new URLSearchParams();
            data.append('flag', 1);
            data.append('userID', user.userId);

            axios({
                url: './getInfo',
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            })
                .then(function (response) {
                    console.log("getInfo 服务器响应数据 carts：");
                    console.log(response.data.carts);
                    that.carts = response.data.carts;
                    for (var i = 0; i < that.carts.length; i++) {
                        that.$set(that.carts[i], 'selected', false);
                        that.sum = 0;
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        btn_add: function(index) {
            this.carts[index].amount += 1;
            this.getsum();
        },
        btn_minute: function(index) {
            this.carts[index].amount -= 1;
            this.getsum();
        },
        tocheckout: function(){
            $.confirm({
                theme: 'supervan',
                title: '',
                content: 'Sure to check out?',
                buttons: {
                    Yes: function () {
                        cartvue.checkout();
                    },
                    cancel: function () {
                    }
                }
            });
        },
        checkout: function (){
            let that = this;
            let carts = this.carts;
            let selectedcarts = [];
            for (var i in carts) {
                if(carts[i].selected){
                    delete carts[i].selected;
                    //carts[i].product = JSON.stringify(carts[i].product);
                    selectedcarts.push(carts[i]);
                }
            }

            let data = new URLSearchParams();
            data.append('userid', that.user.userId);
            data.append('sumprice', that.sum + that.freight);
            data.append('recadd', that.recadd);
            data.append('cartlist', JSON.stringify(selectedcarts));
            axios({
                url: './cartToorder',
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            })
                .then(function (response) {
                    console.log("cartToorder 服务器响应数据 flag：");
                    console.log(response.data.flag);

                    if(response.data.flag==404){
                        $.confirm({
                            theme: 'modern',
                            title: '',
                            content: 'Your identity is out of date',
                            buttons: {
                                login: function () {
                                    $(window).attr('location', 'account.html');
                                },
                                shopping: function () {
                                    $(window).attr('location', 'index.html');
                                }
                            }
                        });
                    }else if (response.data.flag){
                        $.alert({
                            theme: 'modern',
                            backgroundDismiss: true,
                            title: '',
                            content: 'Checkout Successfully',
                            buttons: {
                                "Go on Shopping": function () {
                                    $(window).attr('location', 'index.html');
                                }
                            }
                        });
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },


        //订单页面
        getorders: function (){
            var that = this;
            let data = new URLSearchParams();
            data.append('flag', 4);
            data.append('userID', that.user.userId);

            axios({
                url: './getInfo',
                method: 'post',
                async: false,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            })
                .then(function (response) {
                    that.orders = response.data.orders;
                    console.log("getInfo 服务器响应数据 orders：")
                    console.log(that.orders);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },


        //修改信息
        modifyusername() {
            let that = this;
            this.$prompt('Your username is '+that.user.name+', Would you modify ?', 'Modify Info', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'Cancel'
            }).then(({ value }) => {
                let data = new URLSearchParams();
                data.append('userid', that.user.userId);
                data.append('name', value);
                axios({
                    url: './modifyuserinfo',
                    method: 'post',
                    async: false,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: data
                })
                    .then(function (response) {
                        console.log("Modify 服务器响应数据 flag：")
                        console.log(response.data.flag);
                        if(response.data.flag==1){
                            that.$message({
                                type: 'success',
                                message: 'Modify successfully ! '
                            });
                        }else{
                            that.$message({
                                type: 'warning',
                                message: 'The username already exists ! '
                            });
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }).catch(() => {
                that.$message({
                    type: 'info',
                    message: 'Modify Canceled'
                });
            });
        },
        modifygender() {
            let that = this;
            this.$confirm('Your gender is '+that.user.gender+', Would you modify ?', 'Modify Info', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'Cancel'
            }).then(({ value }) => {
                let gender = (that.user.gender=='male')?('female'):('male');
                let data = new URLSearchParams();
                data.append('userid', that.user.userId);
                data.append('gender', gender);
                axios({
                    url: './modifyuserinfo',
                    method: 'post',
                    async: false,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: data
                })
                    .then(function (response) {
                        console.log("Modify 服务器响应数据 flag：")
                        console.log(response.data.flag);
                        that.$message({
                            type: 'success',
                            message: 'Modify successfully ! '
                        });
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }).catch(() => {
                that.$message({
                    type: 'info',
                    message: 'Modify Canceled'
                });
            });
        },
        modifyteleNo() {
            let that = this;
            this.$prompt('Your teleNo is '+that.user.teleNo+', Would you modify ?', 'Modify Info', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'Cancel',
                inputPattern: /^1[3456789]\d{9}$/,
                inputErrorMessage: 'wrong teleNo pattern'
            }).then(({ value }) => {
                let data = new URLSearchParams();
                data.append('userid', that.user.userId);
                data.append('teleNo', value);
                axios({
                    url: './modifyuserinfo',
                    method: 'post',
                    async: false,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: data
                })
                    .then(function (response) {
                        console.log("Modify 服务器响应数据 flag：")
                        console.log(response.data.flag);
                        that.$message({
                            type: 'success',
                            message: 'Modify successfully ! '
                        });
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }).catch(() => {
                that.$message({
                    type: 'info',
                    message: 'Modify Canceled'
                });
            });
        },
        modifyaddress() {
            let that = this;
            this.$prompt('Your address is "'+that.user.address+'" , Would you modify ?', 'Modify Info', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'Cancel'
            }).then(({ value }) => {
                let data = new URLSearchParams();
                data.append('userid', that.user.userId);
                data.append('address', value);
                axios({
                    url: './modifyuserinfo',
                    method: 'post',
                    async: false,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: data
                })
                    .then(function (response) {
                        console.log("Modify 服务器响应数据 flag：")
                        console.log(response.data.flag);
                        that.$message({
                            type: 'success',
                            message: 'Modify successfully ! '
                        });
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }).catch(() => {
                that.$message({
                    type: 'info',
                    message: 'Modify Canceled'
                });
            });
        },
        modifyemail() {
            let that = this;
            this.$prompt('Your email is '+that.user.email+', Would you modify ?', 'Modify Info', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'Cancel',
                inputPattern: /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,
                inputErrorMessage: 'wrong e-mail pattern'
            }).then(({ value }) => {
                let data = new URLSearchParams();
                data.append('userid', that.user.userId);
                data.append('email', value);
                axios({
                    url: './modifyuserinfo',
                    method: 'post',
                    async: false,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    data: data
                })
                    .then(function (response) {
                        console.log("Modify 服务器响应数据 flag：")
                        console.log(response.data.flag);
                        that.$message({
                            type: 'success',
                            message: 'Modify successfully ! '
                        });
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }).catch(() => {
                that.$message({
                    type: 'info',
                    message: 'Modify Canceled'
                });
            });
        },
        dateFormat(time) {
            let minutes = time.minutes;
            if(minutes<10){
                minutes = "0"+minutes;
            }
            return time.month+1+"-"+time.date+", "+time.hours+":"+minutes;
        },
        exit(){
            $.confirm({
                title: '',
                content: 'Sure to log out ?',
                theme: 'supervan',
                buttons: {
                    Yes: function () {
                        sessionStorage.clear();
                        $(window).attr('location', 'index.html');
                    },
                    cancel: function () {
                    }
                }
            });
        }
    }
})