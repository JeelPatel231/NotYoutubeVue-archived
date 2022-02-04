<template>
<div class="searchbar">
    <input ref="searchbar" type="text" class="search">
    <div class="search-button" v-on:click="search()">clicc</div>
</div>
</template>

<script>
export default {
    name:"searchbar",
    methods:{
        search(){
            this.$emit('changeQuery',this.$refs.searchbar.value)
            console.log("Search "+this.$refs.searchbar.value)
        }
    }

}
</script>

<style>
.searchbar{
    display: flex;
}
.search-button{
    height: 40px;
    width: 40px;
    border-radius: 100%;
    background: #08f;
}
</style>