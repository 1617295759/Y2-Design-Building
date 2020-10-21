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
        }
    }
})