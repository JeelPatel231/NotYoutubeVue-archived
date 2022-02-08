<template>
  <video
    id="videoplayer"
    data-dashjs-player
    autoplay
    v-bind:src="videourl"
    controls
  ></video>
</template>

<script>
export default {
  name: "videoframe",
  props: ["videourl"],
};
</script>

<style lang="scss" scoped>
#videoplayer {
  width: 100%;
  grid-area: videoplayer;

  @media only screen and (min-width: 1035px) {
    min-width: 640px;
  }
}
</style>
