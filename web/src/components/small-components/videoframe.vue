<template>
  <video id="videoplayer" data-dashjs-player autoplay v-bind:src="videourl" controls></video>
</template>

<script>
export default {
    name:"videoframe",
    props:['videourl']
}
</script>

<style>
#videoplayer{
    width: 100%;
}
</style>