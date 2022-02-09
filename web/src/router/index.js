import { createRouter, createWebHistory } from "vue-router";
import Search from "@/views/Search.vue";
import VideoPlayer from "@/views/VideoPlayer.vue";

const routes = [
  {
    path: "/search/:searchQuery",
    name: "Search",
    component: Search,
    props: true,
  },
  {
    path: "/watch",
    name: "Watch",
    component: VideoPlayer,
    props: true,
  },
];

const router = createRouter({
  mode: "history",
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
