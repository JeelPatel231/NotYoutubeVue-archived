<template>
<div class="searchresults">
    <div class="search">
        <videocard v-for="carddata in apiresponse" :key="carddata" :carddata="carddata"/>
    </div>
</div>
</template>

<script>
import videocard from "../small-components/videocard.vue"
export default {
    name:"cardview",
    components:{
        videocard,
    },
    data(){
        return {
            query:"zutomayo",
            apiresponse : [],
            nextPageUrl: "",
            nextPageId : "",
        }
    },
    methods:{
        apicall(){
            if(this.query===""){return}
            console.log("fetching "+this.query)
            fetch(`${this.$apiHost}/search/${this.query}?id=${this.nextPageId}&url=${this.nextPageUrl}`).then(x=>x.json())
            .then(x=>{
                console.log(x.itemsList);
                this.apiresponse = this.apiresponse.concat(x.itemsList);
                this.nextPageUrl = x.nextPage.url;
                this.nextPageId = x.nextPage.id;
            })
        },    
        getNextPage() {
            window.onscroll = () => {
                let bottomOfWindow = document.documentElement.scrollTop + window.innerHeight === document.documentElement.offsetHeight;
                if (bottomOfWindow) {
                    console.log("bottom")
                    this.apicall()
                }
            }
        }
    },
    created(){
        this.apicall();
        this.getNextPage();
    },
}
</script>

<style>
.searchresults{
    padding:16px 24px;
}
</style>