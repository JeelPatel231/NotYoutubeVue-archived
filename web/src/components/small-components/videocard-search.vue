<template>
  <div class="card">
    <div class="thumbnail flex-center">
      <img
        v-bind:src="carddata.thumbnailUrl"
        v-bind:class="{ channelthumb: !carddata.streamType }"
      />
    </div>
    <div
      class="textcontainer"
      v-bind:class="{ flexcenter: !carddata.streamType }"
    >
      <div class="title" v-bind:class="{ verifiedChannel: carddata.verified }">
        {{ carddata.name }}
      </div>
      <div class="metablock">
        <span class="views" v-if="carddata.subscriberCount"
          >{{ this.nFormatter(carddata.subscriberCount) }} subscribers</span
        >
        <span class="views" v-if="carddata.viewCount">{{
          this.nFormatter(carddata.viewCount)
        }}</span>
        <span class="seperator">•</span>
        <span class="time" v-if="carddata.textualUploadDate">{{
          carddata.textualUploadDate
        }}</span>
        <span class="time" v-if="carddata.streamCount"
          >{{ carddata.streamCount }} videos</span
        >
      </div>
      <div
        v-if="carddata.uploaderAvatarUrl"
        class="uploaderwrapper flex-center"
      >
        <img v-bind:src="carddata.uploaderAvatarUrl" />
        <div
          class="uploaderName"
          v-bind:class="{ verifiedChannel: carddata.uploaderVerified }"
        >
          {{ carddata.uploaderName }}
        </div>
      </div>
      <div class="description">
        {{ carddata.shortDescription }}{{ carddata.description }}
      </div>
      <div v-if="carddata.streamType == 'LIVE_STREAM'" class="livestream">
        LIVE
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "videocardsearch",
  props: ["carddata"],
  methods: {
    // https://stackoverflow.com/questions/9461621/format-a-number-as-2-5k-if-a-thousand-or-more-otherwise-900
    nFormatter(num) {
      if (num === undefined) {
        return "";
      }

      let digits = 1;
      const lookup = [
        { value: 1, symbol: "" },
        { value: 1e3, symbol: "k" },
        { value: 1e6, symbol: "M" },
        { value: 1e9, symbol: "G" },
        { value: 1e12, symbol: "T" },
        { value: 1e15, symbol: "P" },
        { value: 1e18, symbol: "E" },
      ];
      const rx = /\.0+$|(\.[0-9]*[1-9])0+$/;
      var item = lookup
        .slice()
        .reverse()
        .find(function (item) {
          return num >= item.value;
        });

      if (num > 1e7) {
        digits = 0;
      }

      return item
        ? (num / item.value).toFixed(digits).replace(rx, "$1") + item.symbol
        : "0";
    },
    getVideoId(youtubeLink) {
      console.log(youtubeLink);
      return new URL(youtubeLink).searchParams.get("v");
    },
  },
};
</script>

<style lang="scss" scoped>
.card {
  display: flex;
  border-radius: 7px;
  color: #fff;
  padding-top: 16px;
  max-width: 1096px;
}
.thumbnail {
  height: 202px; // youtube has 201.99 something, dont blame me, i am kanging blindy
  width: 360px;

  justify-content: center;
  flex-shrink: 0;
  img {
    object-fit: contain;
    height: 100%;
    width: 100%;

    // reduce size if channel thumbnail, its 88px*88px lol
    &.channelthumb {
      height: 136px;
      width: 136px;
      border-radius: 100%;
    }
  }
}

.title {
  color: #fff;
  font-size: 1.8rem;
  line-height: 2.6rem;
  font-weight: 400;
  white-space: normal;
}

.uploaderwrapper {
  padding: 12px 0;
  img {
    height: 30px;
    border-radius: 15px;
  }
}
.textcontainer {
  color: #aaa;
  font-size: 1.2rem;
  line-height: 1.8rem;
  font-weight: 400;
  padding-left: 16px;
}

.uploaderName {
  padding-left: 8px;
}

.flexcenter {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>
