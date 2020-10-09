$("button").click(function(){
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/x-www-form-urlencoded",
        //请求地址
        url : "./getInfo",
        //数据，json字符串
        data : {
            "flag": 3,
            "commodityID": 2
        },
        //请求成功
        success : function(result) {
            console.log(result);
            $("#a").html(result.products[0].appearance);
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
            alert("错误");
        }
    });

})
// new Vue({
//     el: '#app',
//     data(){
//         return{
//             message: '菜鸟教程'
//         }
//     },
//     methods: {
//         click: function (){
//             axios({
//                 url: './getInfo',
//                 method: 'post',
//                 headers: {
//                     'Content-Type': 'application/x-www-form-urlencoded'
//                 },
//                 data: {
//                     "flag": 3,
//                     "commodityID": 2
//                 }})
//                 .then(function (response) {
//                 console.log(response.products);})
//                 .catch(function (error) {
//                     console.log(error);
//                 });
//         }
//     }
// })