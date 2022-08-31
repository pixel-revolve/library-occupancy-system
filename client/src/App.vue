<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import Index from './views/Index'

export default {
  name: 'app',
  components: {
    Index
  }
}
</script>

<style>
* {
  padding:0;
  margin:0;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
