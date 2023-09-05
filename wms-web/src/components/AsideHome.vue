<script>

export default {
  name: "AsideHome",
  data(){
    return{
     //isCollapse:false
     /* menu:[
        {
          nenuClick:'AdminManger',
          menuName:'管理员管理',
          menuIcon:'el-icon-s-custom'
        },{
          nenuClick:'UserManger',
          menuName:'用户管理',
          menuIcon:'el-icon-user-solid'
        },{
          nenuClick:'StorageManger',
          menuName:'仓库管理',
          menuIcon:'el-icon-s-shop'
        },{
          nenuClick:'GoodTypeManger',
          menuName:'产品分类管理',
          menuIcon:'el-icon-s-grid'
        },{
          nenuClick:'GoodManger',
          menuName:'产品管理',
          menuIcon:'el-icon-s-goods'
        },{
          nenuClick:'RecordManger',
          menuName:'记录管理',
          menuIcon:'el-icon-s-order'
        }
      ]*/
    }
  },
 computed:{
   "menu":{
     get(){
       return this.$store.state.menu
     }
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
      default-active="/HomeHome"
      :collapse="isCollapse"
      :collapse-transition="false"
       router
      style="height: 100vh;
      border-right: none">
    <el-menu-item style="padding: 0;height: 70px" >

      <p style="text-align: center"><i><img src="../assets/WMS_Icons_.png" style="height: 60px;width: 60px;margin-top: 5px"></i></p>

    </el-menu-item>
    <el-menu-item index="/HomeHome">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>

    <el-menu-item :index="'/'+item.menuClick" v-for="(item,i) in menu" :key="i">
      <i :class="item.menuIcon"></i>
      <span slot="title">{{ item.menuName }}</span>
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