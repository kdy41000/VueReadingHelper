import Vue from 'vue'
import App from './App.vue'
import axios from "axios"
import VueRouter from 'vue-router'

Vue.use(VueRouter);   // 애플리케이션 전체에서 사용 가능 하도록 설정

Vue.config.productionTip = false

Vue.prototype.$axios = axios;

new Vue({
  render: h => h(App),
}).$mount('#app')
