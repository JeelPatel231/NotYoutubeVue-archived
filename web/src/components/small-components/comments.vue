<template>
  <div class="comment">
    <img class="uploaderavatar" v-bind:src="comment.uploaderAvatarUrl" />
    <div class="content">
      <div class="name">
        {{ comment.name }}
        <span class="uploaddate">{{ comment.textualUploadDate }}</span>
      </div>
      <div
        class="commenttext"
        ref="comment"
        v-bind:class="{ clamped: collapsed }"
      >
        {{ comment.commentText }}
      </div>
      <div v-if="overflow" class="show" v-on:click="collapsed = !collapsed">
        {{ collapsed ? "Read more" : "Show less" }}
      </div>
      <div class="likes">
        <span class="material-icons">thumb_up</span>
        {{ comment.textualLikeCount }}
        <span class="hearted" v-if="comment.heartedByUploader">
          <img v-bind:src="channelavatar" />
        </span>
      </div>
      <div
        v-if="comment.replies"
        v-on:click="showreplies = !showreplies"
        class="showreplies"
      >
        {{ showreplies ? "▲ Hide replies" : "▼ Show replies" }}
      </div>
      <div v-if="showreplies" class="replies">
        <commentsection
          v-bind:channelavatar="channelavatar"
          v-bind:videoid="videoid"
          v-bind:nextpageurl="comment.replies.url"
          v-bind:nextpageid="comment.replies.id"
          v-bind:replysection="true"
        />
      </div>
    </div>
  </div>
</template>

<script>
import commentsection from "@/components/merged-components/commentsection.vue";
export default {
  name: "comments",
  components: {
    commentsection,
  },
  props: ["comment", "videoid", "channelavatar", "nextpageurl", "nextpageid"],
  data() {
    return {
      collapsed: true,
      overflow: false,
      showreplies: false,
    };
  },
  methods: {
    checkOverflow() {
      let ele = this.$refs.comment;
      this.overflow = ele.offsetHeight < ele.scrollHeight;
    },
  },
  mounted() {
    this.checkOverflow();
  },
};
</script>

<style lang="scss" scoped>
.material-icons {
  font-size: inherit;
  margin-right: 8px;
}
.comment {
  display: flex;
  margin-top: 16px;
}
.uploaderavatar {
  height: 40px;
  width: 40px;
  border-radius: 20px;
  margin-right: 16px;
}
.content {
  display: flex;
  flex-direction: column;
}
.name {
  line-height: 1.8rem;
  font-size: 1.3rem;
  font-weight: 400;
  margin-bottom: 4px;
}
.uploaddate {
  color: #aaa;
}
.commenttext {
  line-height: 2rem;
  font-size: 1.4rem;
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  white-space: pre-line;
  &.clamped {
    -webkit-line-clamp: 3;
  }
}
.likes {
  font-size: 1.3rem;
  line-height: 1.8rem;
  color: #aaa;
  display: flex;
  align-items: center;
  height: 36px;
}
.hearted {
  height: 36px;
  width: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  img {
    height: 16px;
    width: 16px;
    border-radius: 8px;
  }
  &::after {
    content: "♥";
    color: red;
    position: absolute;
    bottom: 4px;
    right: 6px;
    font-size: 1.8rem;
    font-weight: bold;
  }
}
.show {
  line-height: 1.8rem;
  font-size: 1.4rem;
  margin-top: 4px;
  color: #aaa;
  font-weight: bold;
  cursor: pointer;
  &:hover {
    text-decoration: underline;
  }
}
.showreplies {
  line-height: 2rem;
  font-size: 1.4rem;
  color: #3ea6ff;
  font-weight: bolder;
}
</style>
