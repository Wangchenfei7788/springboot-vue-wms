<script>

import AsideHome from "@/components/AsideHome.vue";
import HeaderHome from "@/components/HeaderHome.vue";
import MainHome from "@/components/MainHome.vue";

export default {
  name:"IndexHome",
  components: { HeaderHome, AsideHome},
  data(){
    return{
      isCollapse:false,
      aside_width:'200px',
      icon:"el-icon-s-fold",
      menuHeight: {
        height: "100%",
        background: "#313888"
      },
    }
  },
  created() {
    //动态调整左侧菜单栏高度 document.documentElement.clientHeight
    var docHeight = document.body.scrollHeight;
    this.menuHeight.height = docHeight - 10 + "px";
  },
  methods:{
    doCollapse(){
      console.log(1111)

      this.isCollapse = !this.isCollapse
      if(!this.isCollapse){//展开
        this.aside_width='200px'
        this.icon="el-icon-s-fold"
      }else {//收起
        this.aside_width='64px'
        this.icon=" el-icon-s-unfold"
      }
    }
  }
}
</script>

<template>
  <el-container style="height: 100%;margin: 0;padding: 0; border: 1px solid #eee">
    <el-aside :width="aside_width" :style="menuHeight">
      <AsideHome :isCollapse="isCollapse"></AsideHome>
    </el-aside>

    <el-container style="height:100%">
      <el-header style="text-align: right; font-size: 12px;height: 100%;border-bottom: rgba(168,168,168,0.3) 1px solid">
        <HeaderHome @doCollapse="doCollapse" :icon="icon"></HeaderHome>
      </el-header>

      <el-main style="height: 100%;padding: 0">
        <!-- <MainHome></MainHome> -->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<style scoped>

.el-header {
  background-color: #313888;
  color: #333;
  line-height: 60px;
}

</style>