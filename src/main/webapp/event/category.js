var catevue = new Vue({
    el: '#page-content',
    data: {
        products: [],
        sortchoice: "",
        sorthow: "",
        keyword: ""
    },
    created: function () {
        var that = this;
        var choice = this.getUrlPara("choice");
        var how = this.getUrlPara("key");
        (choice==null)?(choice="Time"):(choice);
        (how==null)?(how="ASC"):(how);
        console.log(choice+' : '+how);

        this.$nextTick(() => {
            that.sortchoice = choice;
            that.sorthow = how;
            that.getProducts(choice,how);

            //价格滑块
            $( "#slider-range" ).slider({
                range: true,
                min: 1000,
                max: 3000,
                values: [ 1000, 3000 ],
                slide: function( event, ui ) {
                    $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
                }
            });
            $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
                " - $" + $( "#slider-range" ).slider( "values", 1 ) );
        });
    },
    methods: {
        getUrlPara: function (variable){
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i=0;i<vars.length;i++) {
                var pair = vars[i].split("=");
                if(pair[0] == variable){
                    pair[1] = pair[1].replace("%20"," ");
                    return pair[1];
                }
            }
            return null;
        },
        getProducts: function (choice,contend) {
            var that = this;
            that.sortchoice = choice;
            that.sorthow = contend;
            let data = new URLSearchParams();
            data.append('choice', choice);
            data.append('key', encodeURI(contend));
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
        },
        searchdomain: function(){
            var that = this;
            let data = new URLSearchParams();
            data.append('choice', "sliderdomain");
            data.append('low', $( "#slider-range" ).slider( "values", 0 ));
            data.append('high', $( "#slider-range" ).slider( "values", 1 ));
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
    },
    watch: {
        keyword(val){
            console.log(val);
            this.getProducts("keywords",val);
        }
    }
})