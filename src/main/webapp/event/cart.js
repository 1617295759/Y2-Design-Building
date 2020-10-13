var cartvue = new Vue({
    el: '#page-content',
    data: {
        carts: [],
        sum: 0,
        recadd : ""
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

        that.$nextTick(() => {
            that.getcarts();
            that.recadd = user.address;
        });
    },
    methods: {
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
            data.append('sumprice', that.sum);
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
                                "login": function () {
                                    $(window).attr('location', 'account.html');
                                },
                                "continue shopping": function () {
                                    $(window).attr('location', 'index.html');
                                }
                            }
                        });
                    }else if (response.data.flag){
                        $.confirm({
                            theme: 'modern',
                            backgroundDismiss: true,
                            title: '',
                            content: 'Checkout Successfully',
                            buttons: {
                                "continue checkout": function () {
                                    that.getcarts();
                                },
                                "shopping": function () {
                                    $(window).attr('index', 'index.html');
                                }
                            }
                        });
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})