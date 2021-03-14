<template>
  <div>
      <h1>사용자 관리</h1>
      <span>검색: </span>
      <select v-on:input="updateValue($event.target.value)"
              id="select_id"
      >
          <option value="all">전체</option>
          <option value="userId">아이디</option>
          <option value="userName">이름</option>
          <option value="userEmail">이메일</option>
      </select>    
      <input type="text" v-model="searchTxt" placeholder="검색어를 입력해주세요." />
      <button @click="searchUserList">검색</button>

      <button @click="excelDownload">엑셀 다운로드</button>

      <p>총: {{totalCnt}}건</p>
      <table border="1">
          <tr>
              <th>No</th>
              <th>Id</th>
              <th>Name</th>
              <th>Email</th>
              <th>Auth</th>
          </tr>    
          <tr v-for="(item,idx) in userList" v-bind:key="idx" @click="userDetail(item)">
            <td>{{ item.no }}</td>
            <td>{{ item.userId }}</td>    
            <td>{{ item.userName }}</td>    
            <td>{{ item.userEmail }}</td>  
            <td>{{ item.userAuth }}</td>   
          </tr>    
      </table>  
      <!-- 페이지네이션 공통함수(부모 -> 자식(데이터전달) -> 자식(페이지네이션 필터링) -> 부모(필터링된 데이터 전달받음)) -->
      <Pagination :list-array="allList" v-on:updatedata="refreshData"/>  
      <UserDetailPopup v-if="isModalViewed" :user-detail="userDetailParam" @close-modal="isModalViewed=false" @update-list="getUserList"/>
  </div>  
</template>

<script>
import Pagination from "../utils/Pagination";
import UserDetailPopup from "./UserDetailPopup";

export default {
  name: 'UserManagement',
  data: function() {
      return {
          allList:[],
          userList:[],
          totalCnt:0,
          selectVal: "all",
          searchTxt: "",
          isModalViewed: false,
          userDetailParam: {}
      }
  },
  components: {
      Pagination, UserDetailPopup
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
      },
      updateValue:function(value) {
          this.selectVal = value;
      },
      searchUserList:function() {
          var param = {};
          param.selectVal = this.selectVal;
          param.searchTxt = this.searchTxt;

          this.$axios.get(`/admin/getUserListFilter`,{params:param})
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
      userDetail:function(param) {
          this.isModalViewed = true;
          this.userDetailParam = param;
      },
      excelDownload:function() {
          var params = {};
          params.header = "No,Id,Name,Email,Auth";
          params.body = this.allList;
          params.fileName = "userManagement";
          console.log("다운로드:",params);
          this.$axios.post(`/excel/exceldownload`,params)
          .then((response) => {
              console.log(response.data);
          })
          .catch((error) => {
              console.log(`[ERROR]${error}`);
          })
      }

  },
  created() {
      this.getUserList();
  }
}
</script>
