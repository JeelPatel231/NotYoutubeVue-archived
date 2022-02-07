<template>
<div class="comment">
    <img v-bind:src="comment.uploaderAvatarUrl">
    <div class="content">
        <div class="name">{{comment.name}}
            <span class="uploaddate">{{comment.textualUploadDate}}</span>
        </div>
        <div class="commenttext clamped">{{comment.commentText}}</div>
        <div class="likes">
            <span class="material-icons">thumb_up</span>
            {{comment.textualLikeCount}}
        </div>
        <span v-if="comment.heartedByUploader">HEART</span>
    </div>
</div>
</template>

<script>
export default {
    name:"comments",
    props:["comment"],
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