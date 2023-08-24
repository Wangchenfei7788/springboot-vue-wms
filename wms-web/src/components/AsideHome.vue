<script>
export default {
  name: "AsideHome",
  data(){
    return{
     //isCollapse:false
    }
  },
  props:{
    isCollapse:Boolean
  }
}
</script>

<template>

  <el-menu
      background-color="#313888"
      text-color="white"
      active-text-color="#313888"
      default-active="/Home"
      :collapse="isCollapse"
      :collapse-transition="false"
      style="height: 100vh;
      border-right: none">
    <el-menu-item style="padding: 0;height: 70px" >

      <p style="text-align: center"><i><img src="../assets/WMS_Icons_.png" style="height: 60px;width: 60px;margin-top: 5px"></i></p>

    </el-menu-item>
    <el-menu-item index="/Home">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>

    <el-menu-item index="/Custom">
      <i class="el-icon-s-custom"></i>
      <span slot="title">管理员管理</span>
    </el-menu-item>

    <el-menu-item index="/User">
      <i class="el-icon-user-solid"></i>
      <span slot="title">用户管理</span>
    </el-menu-item>

    <el-menu-item index="/Shop">
      <i class="el-icon-s-shop"></i>
      <span slot="title">仓库管理</span>
    </el-menu-item>

    <el-menu-item index="/Grid">
      <i class="el-icon-s-grid"></i>
      <span slot="title">产品分类管理</span>
    </el-menu-item>

    <el-menu-item index="/Good">
      <i class="el-icon-s-goods"></i>
      <span slot="title">产品管理</span>
    </el-menu-item>

    <el-menu-item index="/Order">
      <i class="el-icon-s-order"></i>
      <span slot="title">记录管理</span>
    </el-menu-item>

  </el-menu>


</template>

<style scoped>
.el-menu-item.is-active{
  background-color: white !important;
}
.el-menu-item.is-active i{
 color: #3f3c8b !important;
}
.el-menu-item i{
  color: white;
}

</style>