<template>
  <div>
    <div class="videoinfoblock">
      <div class="videotitle">{{ videoinfo.name }}</div>
      <div class="videoinfo">
        <div class="info">
          {{ videoinfo.viewCount }} views
          <span class="seperator">•</span>
          {{ videoinfo.textualUploadDate }}
        </div>
        <div class="buttons">BUTTONS</div>
      </div>
    </div>
    <span class="divider" />
    <div class="channelinfoblock">
      <img class="channelavatar" v-bind:src="videoinfo.uploaderAvatarUrl" />
      <div class="channelinfo">
        <div
          class="channelname"
          v-bind:class="{ verifiedChannel: videoinfo.isUploaderVerified }"
        >
          {{ videoinfo.uploaderName }}
        </div>
        <div class="subscribers"></div>
      </div>
      <!-- <div class="subscribe"></div> -->
    </div>
    <div class="description">
      <div
        class="content"
        v-bind:class="{ collapsed: isCollapsed }"
        v-html="videoinfo.description.content"
      ></div>
      <div class="show" v-on:click="isCollapsed = !isCollapsed">
        SHOW {{ isCollapsed ? "MORE" : "LESS" }}
      </div>
    </div>
    <span class="divider" />
  </div>
</template>

<script>
export default {
  name: "description",
  props: ["videoinfo"],
  data() {
    return {
      isCollapsed: true,
    };
  },
};
</script>

<style lang="scss" scoped>
.videotitle {
  line-height: 2.6rem;
  font-size: 1.8rem;
  padding: 20px 0 0 0;
}
.videoinfo {
  height: 40px;
  display: flex;
  font-size: 1.4rem;
  color: #aaa;
  line-height: 2rem;
  align-items: center;
}
.description {
  margin-left: 64px;
}
.divider {
  height: 1px;
  background: #ffffff30;
  display: block;
}
.channelinfoblock {
  display: flex;
  align-items: center;
  height: 50px;
  padding-top: 16px;

  .channelavatar {
    height: 48px;
    width: 48px;
    margin-right: 16px;
    border-radius: 24px;
  }
  .channelname {
    font-size: 1.6rem;
  }
  .subscribers {
    font-size: 1.3rem;
    height: 1.8rem;
  }
}
.content {
  overflow: hidden;
  font-size: 1.4rem;
  line-height: 2rem;

  &.collapsed {
    height: 60px;
  }
}
.show {
  font-size: 1.2rem;
  line-height: 1.8rem;
  margin-top: 8px;
  margin-bottom: 16px;
}
.buttons {
  margin-left: auto;
}
</style>
