<script>


export default {
  name: "MainHome",
  data() {
    return {
      tableData: []
    }
  },
  methods:{
    loadGet(){
      this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/user/listP',{}).then(res=>res.data).then(res=>{
        console.log(res)
        this.tableData=res
      })
    }
  },
  beforeMount() {
    //this.loadGet();
    this.loadPost()
  }
}
</script>

<template>
  <el-table style="width: 100%"
            stripe
            border
            :data="tableData"
            :header-cell-style="{background:'#f3f6fd' ,color:'#555'}">
    <el-table-column
        type="selection"
        width="55">
    </el-table-column>
    <el-table-column prop="id" label="序号" width="60">
    </el-table-column>
    <el-table-column prop="no" label="账号" width="150">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="130">
    </el-table-column>
    <el-table-column prop="age" label="年龄" width="100">
    </el-table-column>
    <el-table-column prop="sex" label="性别" width="100">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.sex === 1 ? 'primary' : 'danger'"
            disable-transitions>{{scope.row.sex === 1 ? '男' : '女'}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="roleId" label="角色" width="130">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.roleId === 0 ? 'danger' : ( scope.row.roleId === 1 ? 'primary' : 'success')"
            disable-transitions>{{scope.row.roleId === 0 ? '超级管理员' :
                                   ( scope.row.roleId === 1 ? '管理员' : '用户')}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="phone" label="电话" width="180">
    </el-table-column>
    <el-table-column prop="operate" label="操作">
    </el-table-column>
  </el-table>
</template>

<style scoped>

</style>