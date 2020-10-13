var catevue = new Vue({
    el: '#page-content',
    data: {
        products: [],
        sortchoice: "",
        sorthow: ""
    },
    created: function () {
        var that = this;
        var choice = this.getUrlPara("choice");
        var how = this.getUrlPara("how");
        (choice==null)?(choice="Time"):(choice);
        (how==null)?(how="ASC"):(how);
        console.log(choice+' : '+how);

        this.$nextTick(() => {
            that.sortchoice = choice;
            that.sorthow = how;
            that.getProducts(choice,"ASC");
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
        getProducts: function (choice,contend) {
            var that = this;
            let data = new URLSearchParams();
            data.append('searchchoice', choice);
            data.append('searchthing', contend);
            axios({
                url: './product',
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: data
            })
                .then(function (response) {
                    console.log("product服务器响应数据：");
                    console.log(response.data.products);
                    that.products = response.data.products;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})