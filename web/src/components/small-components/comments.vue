<template>
<div class="comment">
    <img class="uploaderavatar" v-bind:src="comment.uploaderAvatarUrl">
    <div class="content">
        <div class="name">{{comment.name}}
            <span class="uploaddate">{{comment.textualUploadDate}}</span>
        </div>
        <div class="commenttext clamped">{{comment.commentText}}</div>
        <div class="likes">
            <span class="material-icons">thumb_up</span>
            {{comment.textualLikeCount}}
             <span class="hearted" v-if="comment.heartedByUploader">
                 <img v-bind:src="channelavatar">
             </span>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name:"comments",
    props:["comment","channelavatar"],
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
}
.uploaderavatar{
    height: 40px;
    width: 40px;
    border-radius: 20px;
    margin-right: 8px;
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
    height: 36px;
}
.hearted{
    height: 36px;
    width: 36px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    img{
        height: 16px;
        width: 16px;
        border-radius: 8px;
    }
    &::after{
        content: '♥';
        color: red;
        position: absolute;
        bottom: 4px;
        right: 6px;
        font-size: 1.8rem;
        font-weight: bold;
    }
}
</style>