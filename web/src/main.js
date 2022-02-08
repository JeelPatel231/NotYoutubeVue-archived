import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

var app = createApp(App);
app.use(router);
app.config.globalProperties.$apiHost = `http://${window.location.host}/api`;
// app.config.globalProperties.$apiHost = `http://localhost:8888/api`; //dev
app.mount("body");
