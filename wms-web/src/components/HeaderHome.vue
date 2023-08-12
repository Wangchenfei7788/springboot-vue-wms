<script>
export default {
  name: "HeaderHome"
}
</script>

<template v-slot:dropdown>
  <div>
   <el-dropdown>
     <span>王晨飞</span>
     <i class="el-icon-setting" style="margin-left: 15px"></i>
     <el-dropdown-menu>
      <el-dropdown-item>个人中心</el-dropdown-item>
      <el-dropdown-item>退出登录</el-dropdown-item>
     </el-dropdown-menu>
  </el-dropdown>
  </div>
</template>

<style scoped>

</style>