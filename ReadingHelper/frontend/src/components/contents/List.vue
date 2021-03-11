<template>
  <div>
      <h1>리스트</h1>
      <table border="1">
          <tr>
              <th>No</th>
              <th>Name</th>
              <th>Age</th>
          </tr>    
          <tr v-for="item in list" v-bind:key="item.index">
            <td>{{ item.index }}</td>
            <td>{{ item.name }}</td>    
            <td>{{ item.age }}</td>    
          </tr>    
      </table>    
  </div>  
</template>

<script>

export default {
  name: 'List',
  data: function() {
      return {
          list:[]
      }
  },
  methods: {
      getList:function() {
          this.$axios.get(`/api/getList`)
          .then((response) => {
            console.log("res:",response);
            this.list = response.data.result;
          })
          .catch((error) => {
              console.log(`[ERROR]${error}`);
          })
      }
  },
  created() {
      this.getList();
  }
}
</script>
