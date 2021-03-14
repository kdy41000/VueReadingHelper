<template> 
<div class="modal"> 
    <div class="overlay" @click="$emit('close-modal')"></div> 
        <div class="modal-card"> 
            <!-- <slot /> -->
            <button class="closePopupBtn" @click="$emit('close-modal')">close</button>
            <p>아이디: {{ userDetail.userId }}</p>
            <p>비밀번호: <input type="password" placeholder="변경할 패스워드를 입력하세요." v-model="userInfo.userPw"/></p>
            <p>이름: <input type="text" placeholder="변경할 이름을 입력하세요." v-model="userInfo.userName"/></p>
            <p>이메일: <input type="email" placeholder="변경할 이메일을 입력하세요." v-model="userInfo.userEmail"/></p>
            <p>권한: 
                <select v-model="userInfo.userAuth">
                    <option value="ADMIN">ADMIN</option>
                    <option value="USER">USER</option>
                </select>    
            </p>
            <button @click="updateUserInfo">수정</button>
        </div> 
    </div> 
</template>
<script> 
export default {
    name: 'UserDetailPopup',
    data: function() {
      return {
          userInfo: {
              userId: "",
              userName: "",
              userPw: "",
              userEmail: "",
              userAuth: ""
          }
      }   
    },
     props: {
        userDetail: {
            type: Object,
            required: true
        }
    },
    methods: {
        getUserInfo:function() {
            this.userInfo.userId = this.userDetail.userId;
            this.userInfo.userName = this.userDetail.userName;
            this.userInfo.userEmail = this.userDetail.userEmail;
            this.userInfo.userAuth = this.userDetail.userAuth;
        },
        updateUserInfo:function() {
          if(this.userInfo.userPw == "") {
              alert("패스워드를 입력하세요.");
              return;
          }  
          
         this.$axios.post(`/admin/updateUserInfo`,this.userInfo)
          .then((response) => {
              if(response.data.result.status)
                alert("수정완료 되었습니다.");
                this.$emit('close-modal');
                this.$emit('update-list');
                return;
          })
          .catch((error) => {
              console.log(`[ERROR]${error}`);
          })
        }
    },
    created() {
        this.getUserInfo();
    }
    
}; 
</script> 
<style> 
/* Modal */
 .modal, 
 .overlay { 
     width: 100%; 
     height: 100%; 
     position: fixed; 
     left: 0; 
     top: 0;
 } 
 .overlay { 
     opacity: 0.5; 
     background-color: black; 
 } 
 .modal-card {
     position: relative; 
     max-width: 80%; 
     margin: auto; 
     margin-top: 30px; 
     padding: 20px; 
     background-color: white; 
     min-height: 500px; 
     z-index: 10; 
     opacity: 1; 
 } 
 .closePopupBtn {
     float: right;
 }
</style>
