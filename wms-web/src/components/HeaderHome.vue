<script>
export default {
  name: "HeaderHome",
  props:{
    icon:String
  },
  methods:{
    toUser(){
      console.log('touser')
    },
    logout(){
      console.log('logout')
    },
    collapse(){
      this.$emit('doCollapse')
    }
  }
}
</script>

<template>
  <div style="display: flex;line-height: 70px">
    <div style="font-size: 21px">
      <i style="color: white;cursor: pointer" :class="icon" @click="collapse"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px">
      <span style="color: white">仓库管理系统</span>
    </div>

   <el-dropdown>
     <span style="color: white">王晨飞</span>
     <i class="el-icon-caret-bottom" style="margin-left: 5px;color: white"></i>
     <el-dropdown-menu slot="dropdown">
      <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
      <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
     </el-dropdown-menu>
  </el-dropdown>
  </div>
</template>

<style scoped>

</style>