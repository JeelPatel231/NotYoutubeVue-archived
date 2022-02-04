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
            page:0,
        }
    },
    methods:{
        apicall(){
            if(this.query===""){return}
            console.log("fetching "+this.page)
            fetch(`${this.$hostname}/api/search/${this.query}/${this.page}`).then(x=>x.json())
            .then(x=>{
                console.log(this.apiresponse.concat(x));
                this.apiresponse = this.apiresponse.concat(x);
            })
            this.page++
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
        this.getNextPage()
    }
}
</script>

<style>
.searchresults{
    padding:16px 24px;
}
</style>