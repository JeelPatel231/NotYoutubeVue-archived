<template>
  <comments
    v-bind:channelavatar="channelavatar"
    v-for="comment in apiresponse"
    :key="comment"
    v-bind:comment="comment"
  />
</template>

<script>
import comments from "@/components/small-components/comments.vue";
export default {
  name: "commentsection",
  props: ["videoid", "channelavatar", "nextPageUrl", "nextPageId"],
  components: {
    comments,
  },
  data() {
    return {
      apiresponse: [],
      componentNextPageId: this.nextPageId,
      componentNextPageUrl: this.nextPageUrl,
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
          console.log(x.itemsList);
          this.apiresponse = this.apiresponse.concat(x.itemsList);
          this.componentNextPageUrl = x.nextPage.url;
          this.componentNextPageId = x.nextPage.id;
        });
    },
    getNextPage() {
      window.onscroll = () => {
        let bottomOfWindow =
          document.documentElement.scrollTop + window.innerHeight ===
          document.documentElement.offsetHeight;
        if (bottomOfWindow) {
          console.log("bottom");
          this.apicall();
        }
      };
    },
  },
  created() {
    this.apicall();
    this.getNextPage();
  },
};
</script>

<style></style>
