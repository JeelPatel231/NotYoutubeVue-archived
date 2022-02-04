<template>
  <slide-view/>
</template>

<script>
import SlideView from '@/components/merged-components/slide-view.vue';

export default {
  name: "App",
  props:['videoid'],
  components: {
    SlideView
  },
};
</script>

<style lang="scss">

</style>
