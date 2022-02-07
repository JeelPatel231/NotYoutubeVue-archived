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
  props: ["videoid", "channelavatar"],
  components: {
    comments,
  },
  data() {
    return {
      apiresponse: [],
      nextPageUrl: "",
      nextPageId: "",
    };
  },
  methods: {
    apicall() {
      if (this.query === "") {
        return;
      }
      console.log("fetching " + this.videoid + " comments");
      fetch(
        `${this.$apiHost}/comments/${this.videoid}?id=${this.nextPageId}&url=${this.nextPageUrl}`
      )
        .then((x) => x.json())
        .then((x) => {
          console.log(x.itemsList);
          this.apiresponse = this.apiresponse.concat(x.itemsList);
          this.nextPageUrl = x.nextPage.url;
          this.nextPageId = x.nextPage.id;
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
