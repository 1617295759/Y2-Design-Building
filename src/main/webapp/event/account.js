var countvue = new Vue({
    el: '#page-content',
    data: {
    },
    created: function () {
        let user = JSON.parse(sessionStorage.getItem("user"));
        console.log(user);
        if (user != null) {
            $(window).attr('location', 'cart.html');
        }
    },
    methods: {
        login: function () {
            var username = $("#lname").val();
            var password = $("#lpassword").val();
            $.ajax({
                //请求方式
                type: "POST",
                //请求的媒体类型
                contentType: "application/x-www-form-urlencoded",
                async: false,
                //请求地址
                url: "./login",
                //数据，json字符串
                data: {
                    "username": username,
                    "password": password
                },
                //请求成功
                success: function (result) {
                    switch (result.flag) {
                        case 0:
                            $.alert({
                                title: 'Sorry',
                                content: "the username doesn't exist",
                            });
                            break;
                        case 1:
                            sessionStorage.setItem("user", JSON.stringify(result.user));
                            $.confirm({
                                title: 'login successfully',
                                content: 'back to HOME',
                                buttons: {
                                    Yes: function () {
                                        $(window).attr('location', 'index.html');
                                    }
                                }
                            });
                            break;
                        case 2:
                            $.alert({
                                title: 'Sorry',
                                content: 'the password is wrong',
                            });
                            break;
                    }
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        },
        register: function (){
            var username = $("#rname").val();
            var password = $("#rpassword").val();
            var gender = $("input[name='gender']:checked").val();
            var teleno = $("#phone").val();
            var email = $("#email").val();
            var address = $("#address").val();
            var repassword = $("#rrepassword").val();
            var flag = true;

            if (password !== repassword) {
                flag = false;
                alert("两次密码不同");
                return;
            }
            var phonereg = /^1(3|4|5|6|7|8|9)\d{9}$/;
            var regemail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if (!regemail.test(email)) {
                flag = false;
                alert("please type in the right email address");
                return;
            }
            if (!phonereg.test(teleno)) {
                flag = false;
                alert("please type in the right mobile phone");
                return;
            }
            if (gender == null) {
                flag = false;
                alert("please type in the gender");
                return;
            }
            if (flag == true) {
                $.ajax({
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    async: false,
                    url: "./register",
                    data: {
                        "username": username,
                        "password": password,
                        "gender": gender,
                        "email": email,
                        "address": address,
                        "teleno": teleno
                    },
                    //请求成功
                    success: function (result) {
                        if (result.flag) {
                            sessionStorage.setItem("user", JSON.stringify(result.user));
                            $.confirm({
                                title: 'register successfully',
                                content: 'back to HOME',
                                buttons: {
                                    Yes: function () {
                                        $(window).attr('location', 'index.html');
                                    }
                                }
                            });

                        } else {
                            $.alert({
                                title: 'Sorry',
                                content: 'the username has been used',
                            });
                        }
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });
            }
        }
    }
})