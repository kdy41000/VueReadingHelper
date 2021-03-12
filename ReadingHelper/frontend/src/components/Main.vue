<template>
  <div>
    <div v-if="userInfo.userAuth == 'USER'" class="header">
      여기
      <UserHeader/>
    </div>
    <div v-if="userInfo.userAuth == 'ADMIN'" class="header">
      관리자
      <AdminHeader/>
    </div>
    <div class="contents">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import VueRouter from 'vue-router';  //vue-router import
import UserHeader from "./sementics/UserHeader";
import AdminHeader from "./sementics/AdminHeader";
import Home from "./contents/Home";
import AboutUs from "./contents/AboutUs";
import UserManagement from "./admin/UserMangement";
import Logout from "./auth/Logout";

const router = new VueRouter({  // Vue인스턴스 생성 시 router객체 지정
  routes : [   //명명된 라우트 사용
    { path:'/home', name: 'home', component: Home },
    { path: '/aboutus', name:'aboutus', component: AboutUs },
    { path: '/usermanage', name:'usermanage', component: UserManagement },
    { path: '/logout', name:'logout', component: Logout }
  ]
})

export default {
  name: 'Main',
  components:{ UserHeader, AdminHeader },
  router,
  data: function() {
      return {
          userInfo: {
            userId:{},
            userAuth:{}
          }
      }
  },
  methods: {
      getAuth:function() {
          this.$axios.get(`/login/getAuth`)
          .then((response) => {
            this.userInfo.userId = response.data.userId;
            this.userInfo.userAuth = response.data.userAuth[0].authority;

            console.log("userInfo:",this.userInfo);
          })
          .catch((error) => {
              console.log(`[ERROR]${error}`);
          })
      }
  },
  created() {
    this.getAuth();
  }
}
</script>

<style>
</style>
