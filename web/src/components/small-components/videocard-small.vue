<template>
  <a v-bind:href="'/watch?v=' + video.url.split('v=')[1]">
    <!-- BAD ^^^^^^ I KNOW, but the component doesnt re-render again because of nested self-dependency in comment section, so a link for now -->
    <div class="videocardsmall">
      <img v-bind:src="video.thumbnailUrl" />
      <div class="metadatawrapper">
        <div class="title">
          {{ video.name }}
        </div>
        <div class="metadata">
          <div>{{ video.uploaderName }}</div>
          <div>
            {{ video.viewCount }}
            <span class="seperator">•</span>
            {{ video.textualUploadDate }}
          </div>
        </div>
      </div>
    </div>
  </a>
</template>

<script>
export default {
  name: "videoCardSmall",
  props: ["video"],
};
</script>

<style lang="scss" scoped>
a {
  color: inherit;
}
.videocardsmall {
  display: flex;
  margin-bottom: 8px;

  img {
    height: 94px;
    width: 168px;
  }
  .title {
    font-size: 1.4rem;
    line-height: 2rem;
    text-overflow: ellipsis;
    overflow: hidden;
    -webkit-line-clamp: 2;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    margin-bottom: 8px;
  }
  .metadatawrapper {
    display: flex;
    flex-direction: column;
    margin-left: 8px;
    line-height: 2rem;
  }
  .metadata {
    font-size: 1.2rem;
    color: #aaa;
  }
}
</style>
