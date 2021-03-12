<template>
  <div>
      <h1>사용자 관리</h1>
      <p>총:{{totalCnt}}</p>
      <table border="1">
          <tr>
              <th>No</th>
              <th>Id</th>
              <th>Name</th>
              <th>Email</th>
              <th>Auth</th>
          </tr>    
          <tr v-for="(item,idx) in userList" v-bind:key="idx">
            <td>{{ item.no }}</td>
            <td>{{ item.userId }}</td>    
            <td>{{ item.userName }}</td>   
            <td>{{ item.userEmail }}</td>  
            <td>{{ item.userAuth }}</td>   
          </tr>    
      </table>  
      <!-- 페이지네이션 공통함수(부모 -> 자식(데이터전달) -> 자식(페이지네이션 필터링) -> 부모(필터링된 데이터 전달받음)) -->
      <Pagination :list-array="allList" v-on:updatedata="refreshData"/>  
  </div>  
</template>

<script>
import Pagination from "../utils/Pagination";

export default {
  name: 'UserManagement',
  data: function() {
      return {
          allList:[],
          userList:[],
          totalCnt:0
      }
  },
  components: {
      Pagination
  },
  watch: {
    /*customer_data: function(param) {
      console.log('test:',param);
    }*/
  },
  methods: {
      getUserList:function() {
          this.$axios.get(`/admin/getUserList`)
          .then((response) => {
           for(var i = 0; i < response.data.result.length; i++) {
               response.data.result[i].no = i + 1;
           }
           
           this.totalCnt = response.data.result.length;
           this.allList = response.data.result;
          })
          .catch((error) => {
              console.log(`[ERROR]${error}`);
          })
      },
      refreshData:function(paramArray) {
          this.userList = paramArray;
      }

  },
  created() {
      this.getUserList();
  }
}
</script>
