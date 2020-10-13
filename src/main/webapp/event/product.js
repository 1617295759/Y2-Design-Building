var catevue = new Vue({
    el: '#page-content',
    data: {
        product: {},
        amount: 1
    },
    created: function () {
        var that = this;
        var commodityID = this.getUrlPara("commodityid");
        console.log('commodityID '+commodityID);

        this.$nextTick(() => {
            that.getProduct(commodityID);
        });
    },
    methods: {
        getUrlPara: function (name){
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        },
        getProduct: function (commodityID) {
            var that = this;
            let data = new URLSearchParams();
            data.append('flag', 3);
            data.append('commodityID', commodityID);
            axios({
                url: './getInfo',
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            })
                .then(function (response) {
                    console.log("getInfo服务器响应数据：");
                    console.log(response.data.product);
                    that.product = response.data.product;
                })
                .catch(function (error) {
                    console.log(error);
                    $.confirm({
                        title: '',
                        content: 'This commodity is off the shelves',
                        theme: 'supervan',
                        buttons: {
                            back: function () {
                                $(window).attr('location', 'index.html');
                            }
                        }
                    });
                });
        },
        addtocart: function () {
            let that = this;
            let user = JSON.parse(sessionStorage.getItem("user"));
            if (user == null) {
                $.confirm({
                    title: 'LogIn first',
                    content: 'go to login',
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
            let data = new URLSearchParams();
            data.append('userID', user.userId);
            data.append('amount', this.amount);
            data.append('commodityID', this.product.commodityId);
            axios({
                url: './addintocart',
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            })
                .then(function (response) {
                    console.log("addintocart服务器响应数据：");
                    console.log(response.data.flag);
                    if (response.data.flag){
                        $.confirm({
                            title: '',
                            content: 'Add to cart successfully ！',
                            theme: 'modern',
                            backgroundDismiss: true,
                            buttons: {
                                back: function () {
                                    $(window).attr('location', 'cart.html');
                                }
                            }
                        });
                    }else{
                        $.confirm({
                            title: '',
                            content: 'Some Problem, Please try again later',
                            theme: 'supervan',
                            buttons: {
                                back: function () {
                                    $(window).attr('location', 'index.html');
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