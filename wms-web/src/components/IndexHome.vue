<script>

import AsideHome from "@/components/AsideHome.vue";
import HeaderHome from "@/components/HeaderHome.vue";
import MainHome from "@/components/MainHome.vue";

export default {
  name:"IndexHome",
  components: {MainHome, HeaderHome, AsideHome},

}
</script>

<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246);height: 100%;">
      <AsideHome></AsideHome>
    </el-aside>

    <el-container style="height:100%">
      <el-header style="text-align: right; font-size: 12px;height: 100%;border-bottom: rgba(168,168,168,0.3) 1px solid">
        <HeaderHome></HeaderHome>
      </el-header>

      <el-main style="height: 100%">
        <MainHome></MainHome>
      </el-main>
    </el-container>
  </el-container>

</template>

<style scoped>
.el-header {
  //background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
.el-main {
  padding: 5px;
}
.el-aside {
  color: #333;
}
</style>