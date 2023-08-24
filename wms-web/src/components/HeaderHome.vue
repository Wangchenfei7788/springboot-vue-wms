<script>
export default {
  name: "HeaderHome",
  data(){
    return {
      user:JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  props:{
    icon:String
  },
  methods:{
    toUser(){
      console.log('touser')
    },
    logout(){
      console.log('logout')


      this.$confirm('确定退出登录吗？', '警告', {
        confirmButtonText: '确定',  //确认按钮的文字显示
        type: 'warning',
        //center: true, //文字居中显示

      })
          .then(() => {
           this.$message({
             type:"success",
             message:"已退出登录"
           })
            this.$router.push('/')
            sessionStorage.clear()
          })
          .catch(() => {
            this.$message({
              type:"info",
              message:"已取消退出登录"
            })
          });


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
     <span style="color: white">{{user.name}}</span>
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