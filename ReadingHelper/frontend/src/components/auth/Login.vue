<template>
  <div>
      <div class="header">
        <h1>Reading Helper</h1>
      </div>
      <hr/>
      <h1>Login</h1>
      <input type="text" v-model="userId" placeholder="Id를 입력해주세요."/><br/>
      <input type="password" v-model="userPw" placeholder="Pw를 입력해주세요."/><br/>
      <button @click="loginRes()">Login</button>  
  </div>  
</template>

<script>

export default {
  name: 'Login',
  data: function() {
      return {
          userId:'',
          userPw:''
      }
  },
  methods: {
      loginRes:function() {
          var userInfo = {};
          userInfo.userId = this.userId;
          userInfo.userPw = this.userPw;
          console.log("userInfo:",userInfo);

          var form = document.createElement("form");
          form.setAttribute("charset","UTF-8");
          form.setAttribute("method","post");
          form.setAttribute("action","/loginRes");

          var hiddenField = document.createElement("input");
          hiddenField.setAttribute("type","hidden");
          hiddenField.setAttribute("name","userId");
          hiddenField.setAttribute("value",this.userId);
          form.appendChild(hiddenField);

          hiddenField = document.createElement("input");
          hiddenField.setAttribute("type","hidden");
          hiddenField.setAttribute("name","userPw");
          hiddenField.setAttribute("value",this.userPw);
          form.appendChild(hiddenField);
 
          document.body.appendChild(form);
          form.submit();

      }
  }
}
</script>
