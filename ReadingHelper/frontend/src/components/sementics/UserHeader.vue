<template>
  <div>
      <h1>Reading Helper</h1>
      <nav>
        <ul>
          <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
          <li><router-link v-bind:to="{ name: 'aboutus' }">AboutUs</router-link></li>
          <li><router-link v-bind:to="{ name: 'logout' }">로그아웃</router-link></li>
        </ul>  
      </nav> 
    <hr/>
  </div>  
</template>

<script>

export default {
  name: 'UserHeader'
}
</script>
