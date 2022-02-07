<template>
<div class="flexparent">
<div class="flexpage">
<div class="primary">
  <videoframe v-bind:videourl="apiResponse.videoStreams.at(-1).url" />
  <description v-bind:videoinfo="apiResponse.videoinfo"/>
  <commentsection v-bind:videoid="videoid"/>
</div>
<div class="secondary">
  <videocardsmall v-for="video in apiResponse.relatedItems.itemList" :key="video" :video="video" />
</div>
</div>
</div>
</template>

<script>
import videoframe from "@/components/small-components/videoframe.vue"
import description from '@/components/small-components/description.vue'
import videocardsmall from '@/components/small-components/videocard-small.vue'
import commentsection from '@/components/merged-components/commentsection.vue'


export default {
    name:"VideoPlayer",
    props:['videourl','videoid','videoinfo'],
    components:{
        videoframe,
        description,
        videocardsmall,
        commentsection,
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
.flexparent{
  display: flex;
  justify-content: center;
}
.flexpage{
  display: flex;
  flex-wrap: wrap;
  max-width: 1754px;
}
.primary{
  min-width: 640px;
  padding: 24px 24px 0 24px;
  color:#fff;
  flex: 1;
}
.secondary{
  padding: 24px 24px 0 0;
  color: white;
  max-width: 400px;
  min-width: 320px;
  flex: 1;
}
</style>