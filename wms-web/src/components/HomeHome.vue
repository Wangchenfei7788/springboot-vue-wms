<script>
import DateUtils from "./DateUtils";

export default {
  name: "HomeHome",
  components: {DateUtils},
  data() {
    return {
      user: {}
    }
  },
    computed:{},
  methods: {
    init() {
      this.user = JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  created() {
    this.init()
  }
}

</script>

<template>
  <div style="text-align: center;background-color: #f1f1f3;height: 100%;padding: 0px;margin: 0px;">
    <h1 style="font-size: 50px;">{{ '欢迎你！' + user.name }}</h1>
    <el-descriptions title="个人中心" style="margin: 5px;" :column="2" size="40" border>
      <el-descriptions-item >
        <template slot="label">
          <i class="el-icon-s-custom"></i>
          账号
        </template>
          {{ user.no }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-s-promotion"></i>
          电话
        </template>
        {{ user.phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location"></i>
          性别
        </template>
        <el-tag
            :type="user.sex == '1' ? 'primary' : 'danger'"
            disable-transitions><i
            :class="user.sex==1?'el-icon-male':'el-icon-female'"></i>{{ user.sex == 1 ? "男" : "女" }}
        </el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-s-order"></i>
          角色
        </template>
        <el-tag
            :type="user.roleId === 0 ? 'danger' : ( user.roleId === 1 ? 'primary' : 'success')"
            disable-transitions>{{user.roleId == 0 ? '超级管理员' :
            (user.roleId == 1 ? '管理员' : '用户')}}
        </el-tag>

      </el-descriptions-item>
    </el-descriptions>

    <DateUtils></DateUtils>
  </div>
</template>

<style scoped>

</style>