<template>
<div class="primary">
  <videoframe v-bind:videourl="apiResponse.videoStreams.at(-1).url" />
  <description v-bind:videoinfo="apiResponse.videoinfo"/>
</div>
</template>

<script>
import videoframe from "@/components/small-components/videoframe.vue"
import Description from '../components/small-components/description.vue'

export default {
    name:"VideoPlayer",
    props:['videourl','videoid','videoinfo'],
    components:{
        videoframe,
        Description,
    },
    data(){
      return{
        apiResponse : null,
      }
    },
    methods:{
      fetchUrls(){
        fetch(`${this.$apiHost}/video/${this.videoid}`)
        .then(x=>x.json())
        .then(x=>{console.log(x);this.apiResponse = x})
      }
    },
    created(){
      this.fetchUrls()
    }
}
</script>

<style>

</style>