import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import VideoPlayer from "@/views/VideoPlayer.vue"

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path : "/video/:videoid",
    name : "VideoPlayer",
    component : VideoPlayer,
    props: true,
  },
];

const router = createRouter({
  mode: 'history',
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
