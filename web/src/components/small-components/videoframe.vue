<template>
  <video data-dashjs-player autoplay v-bind:src="directLink" controls></video>
</template>

<script>
export default {
    name:"videoframe",
    props:['videoid'],
    data(){
        return{
            directLink : null,
        }
    },
    methods:{
        fetchUrls(videoid){
            fetch(`${this.$hostname}/api/video/${videoid}`)
            .then(x=>x.json())
            .then(x=>{console.log(x);this.directLink = x[x.length-1].url})
        }
    },
    created(){
        this.fetchUrls(this.videoid)
    }
}
</script>

<style>

</style>