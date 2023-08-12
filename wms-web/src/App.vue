<template>
  <div id="app">
    <IndexHome></IndexHome>
  </div>
</template>

<script>


import IndexHome from "@/components/IndexHome.vue";


export default {
  name: 'App',
  components: {
    IndexHome,

  }
}
</script>

<style>
#app {
  height: 100%;
}
</style>
