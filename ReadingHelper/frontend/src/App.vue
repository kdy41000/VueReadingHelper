<template>
  <div>
    <div class="contents">
      <router-view></router-view>
    </div>
  </div>
</template> 

<script>
import VueRouter from 'vue-router';  //vue-router import
import Login from "./components/auth/Login";
import Main from "./components/Main";

const router = new VueRouter({  // Vue인스턴스 생성 시 router객체 지정
  routes : [   //명명된 라우트 사용
    { path:'/', component: Login },
    { path:'/main', name: 'main', component: Main }
  ]
})

export default {
  name: 'App',
  router
}
</script>

<style>
</style>
