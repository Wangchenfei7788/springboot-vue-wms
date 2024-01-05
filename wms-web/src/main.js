import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";
import './assets/global.css'
import VueRouter from "vue-router";
import router from "@/router";
import store from "./store";

Vue.use(ElementUI,{size:'small'});
Vue.use(VueRouter);
Vue.prototype.$axios=axios;
Vue.prototype.$httpUrl='http://localhost:8090'
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
