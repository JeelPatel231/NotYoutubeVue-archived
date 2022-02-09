<template>
  <div class="flexparent">
    <div class="gridpage">
      <videoframe v-bind:videourl="apiResponse.videoStreams.at(-1).url" />
      <description v-bind:videoinfo="apiResponse.videoinfo" />
      <div class="commentsection">
        <commentsection
          v-bind:channelavatar="apiResponse.videoinfo.uploaderAvatarUrl"
          v-bind:videoid="videoid"
        />
      </div>
      <div class="secondary">
        <videocardsmall
          v-for="video in apiResponse.relatedItems.itemList"
          :key="video"
          :video="video"
        />
        <span class="divider" />
      </div>
    </div>
  </div>
</template>

<script>
import videoframe from "@/components/small-components/videoframe.vue";
import description from "@/components/small-components/description.vue";
import videocardsmall from "@/components/small-components/videocard-small.vue";
import commentsection from "@/components/merged-components/commentsection.vue";

export default {
  name: "VideoPlayer",
  props: ["videourl", "videoinfo", "channelavatar"],
  components: {
    videoframe,
    description,
    videocardsmall,
    commentsection,
  },
  data() {
    return {
      apiResponse: null,
      videoid: this.$route.query.v,
    };
  },
  methods: {
    fetchUrls() {
      fetch(`${this.$apiHost}/video/${this.videoid}`)
        .then((x) => x.json())
        .then((x) => {
          console.log(x);
          this.apiResponse = x;
        });
    },
  },
  created() {
    this.fetchUrls();
  },
};
</script>

<style lang="scss" scoped>
.gridpage {
  padding: 24px;
  display: grid;
  column-gap: 24px;
  max-width: 1754px;

  grid-template:
    "videoplayer related"
    "description none"
    "comments none";

  @media only screen and (max-width: 1034px) {
    grid-template:
      "videoplayer"
      "description"
      "related"
      "comments";
  }
}

.flexparent {
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
}
.secondary {
  color: white;
  grid-area: related;
  margin-top: 8px;
  @media only screen and (min-width: 1035px) {
    grid-row: 1 / -1;
    max-width: 400px;
    min-width: 320px;
  }
}
.commentsection {
  grid-area: comments;
}
</style>
