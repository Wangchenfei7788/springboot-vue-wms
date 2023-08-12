<script>
export default {
  name: "HeaderHome",
  methods:{
    toUser(){
      console.log('touser')
    },
    logout(){
      console.log('logout')
    }
  }
}
</script>

<template>
  <div style="display: flex;line-height: 60px">
    <div style="font-size: 21px">
      <i class="el-icon-s-fold"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px">
      <span>仓库管理系统</span>
    </div>

   <el-dropdown>
     <span>王晨飞</span>
     <i class="el-icon-caret-bottom" style="margin-left: 5px"></i>
     <el-dropdown-menu slot="dropdown">
      <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
      <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
     </el-dropdown-menu>
  </el-dropdown>
  </div>
</template>

<style scoped>

</style>