<template>
  <div>
     
  </div>  
</template>

<script>

export default { 
  name: 'Logout',
  data: function() {
      return {
          userId:'',
          userPw:'' 
      }
  },
  methods: { 
       logoutRes:function() { 
          var userInfo = {};
          userInfo.userId = this.userId;
          userInfo.userPw = this.userPw; 
          console.log("userInfo:",userInfo);

          var form = document.createElement("form");
          form.setAttribute("charset","UTF-8");
          form.setAttribute("method","post");
          form.setAttribute("action","/logout");

          document.body.appendChild(form);
          form.submit();
       }
  },
  created() {
      this.logoutRes();
  }
}
</script>
