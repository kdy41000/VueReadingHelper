<template>
  <div>
    <div class="header">
      <h1>Reading Helper</h1>
      <nav>
        <ul>
          <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
          <li><router-link v-bind:to="{ name: 'list' }">리스트</router-link></li>
        </ul>  
      </nav>  
    </div> 
    <hr/>
    <div class="contents">
      <router-view></router-view>
    </div> 
  </div>
</template>

<script>
import VueRouter from 'vue-router';  //vue-router import
import Home from "./components/contents/Home";
import List from "./components/contents/List";

const router = new VueRouter({  // Vue인스턴스 생성 시 router객체 지정
  routes : [   //명명된 라우트 사용
    { path:'/', component: Home },
    { path:'/', name: 'home', component: Home },
    { path: '/list', name:'list', component: List }
  ]
})

export default {
  name: 'App',
  router
}
</script>

<style>
</style>
