new Vue({
    el: '#page-content',
    data(){
        return{
            cart: null
        }
    },
    mounted: function(){
        let user=JSON.parse(sessionStorage.getItem("user"));
        if (user==null){
            alert("请先登录");

        }
        console.log(user);
        alert(user.userId);
        let data= new URLSearchParams();
        data.append('flag', 1);
        data.append('userID', user.userId);

        axios({
            url: './getInfo',
            method: 'post',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: data})
            .then(function (response) {
                console.log(response.data);})
            .catch(function (error) {
                console.log(error);
                alert(error);
            });
    },
    methods: {

    }
})