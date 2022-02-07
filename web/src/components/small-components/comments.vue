<template>
  <div v-for="comments in apiresponse" :key="comments" class="comment">
      <img v-bind:src="comments.uploaderAvatarUrl">
      <div class="content">
            <div class="name">{{comments.name}}
                <span class="uploaddate">{{comments.textualUploadDate}}</span>
            </div>
            <div class="commenttext clamped">{{comments.commentText}}</div>
            <div class="likes">
                <span class="material-icons">thumb_up</span>
            {{comments.textualLikeCount}}
            </div>
            <span v-if="comments.heartedByUploader">HEART</span>
    </div>
  </div>
</template>

<script>
export default {
    name:"comments",
    props:["comment","videoid"],
    data(){
        return {
            apiresponse : [],
            nextPageUrl: "",
            nextPageId : "",
        }
    },
    methods:{
        apicall(){
            if(this.query===""){return}
            console.log("fetching "+this.videoid+" comments")
            fetch(`${this.$apiHost}/comments/${this.videoid}?id=${this.nextPageId}&url=${this.nextPageUrl}`).then(x=>x.json())
            .then(x=>{
                console.log(x.itemsList);
                this.apiresponse = this.apiresponse.concat(x.itemsList);
                this.nextPageUrl = x.nextPage.url;
                this.nextPageId = x.nextPage.id;
            })
        },    
        getNextPage() {
            window.onscroll = () => {
                let bottomOfWindow = document.documentElement.scrollTop + window.innerHeight === document.documentElement.offsetHeight;
                if (bottomOfWindow) {
                    console.log("bottom")
                    this.apicall()
                }
            }
        }
    },
    created(){
        this.apicall();
        this.getNextPage();
    },
}
</script>

<style lang="scss" scoped>
.material-icons{
    font-size: inherit;
    margin-right: 8px;
}
.comment{
    display: flex;
    margin-top: 16px;
    img{
        height: 40px;
        width: 40px;
        border-radius: 20px;
        margin-right: 8px;
    }
}
.content{
    display: flex;
    flex-direction: column;
}
.name{
    line-height:1.8rem;
    font-size: 1.3rem;
    font-weight: 400;
    margin-bottom: 4px;
}
.uploaddate{
    color: #aaa;
}
.commenttext{
    line-height: 2rem;
    font-size: 1.4rem;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    margin-bottom: 8px;
    &.clamped{
        -webkit-line-clamp: 3;
    }
}
.likes{
    font-size: 1.3rem;
    line-height: 1.8rem;
    color: #aaa;
    display: flex;
    align-items: center;
}
</style>