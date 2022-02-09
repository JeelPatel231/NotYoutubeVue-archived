<template>
  <div class="commentsection-header">Comments</div>
  <comments
    v-bind:channelavatar="channelavatar"
    v-for="comment in apiresponse"
    :key="comment"
    v-bind:comment="comment"
    v-bind:videoid="videoid"
  />
  <div
    v-if="replysection && pageEnd"
    v-on:click="this.apicall()"
    class="fetchmore"
  >
    Show more
  </div>
</template>

<script>
import { defineAsyncComponent } from "vue";

export default {
  name: "commentsection",
  props: [
    "videoid",
    "channelavatar",
    "nextpageurl",
    "nextpageid",
    "replysection",
  ],
  components: {
    comments: defineAsyncComponent(() =>
      import("@/components/small-components/comments.vue")
    ),
  },
  data() {
    return {
      apiresponse: [],
      pageEnd: false,
      componentNextPageId: this.nextpageid,
      componentNextPageUrl: this.nextpageurl,
    };
  },
  methods: {
    apicall() {
      if (this.query === "") {
        return;
      }
      console.log("fetching " + this.videoid + " comments");
      let url = ""; // need sanitizing
      if (
        this.componentNextPageId == undefined &&
        this.componentNextPageUrl == undefined
      ) {
        url = `${this.$apiHost}/comments/${this.videoid}`;
      } else {
        url = `${this.$apiHost}/comments/${this.videoid}?id=${this.componentNextPageId}&url=${this.componentNextPageUrl}`;
      }
      fetch(url)
        .then((x) => x.json())
        .then((x) => {
          console.log(x);
          this.apiresponse = this.apiresponse.concat(x.itemsList);
          this.pageEnd = !!x.nextPage;
          if (x.nextPage) {
            this.componentNextPageUrl = x.nextPage.url;
            this.componentNextPageId = x.nextPage.id;
          }
        });
    },
    getNextPage() {
      if (!this.replysection) {
        window.onscroll = () => {
          let bottomOfWindow =
            document.documentElement.scrollTop + window.innerHeight ===
            document.documentElement.offsetHeight;
          if (bottomOfWindow) {
            console.log("bottom");
            this.apicall();
          }
        };
      }
    },
  },
  created() {
    this.apicall();
    this.getNextPage();
  },
};
</script>

<style lang="scss" scoped>
.commentsection-header {
  line-height: 2rem;
  font-size: 1.8rem;
  margin-top: 24px;
}
.fetchmore {
  line-height: 2rem;
  font-size: 1.4rem;
  color: #3ea6ff;
  font-weight: bolder;
}
</style>
