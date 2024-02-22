<script>


export default {
  name: "RegisterHome",
  data() {
    let checkAge = (rule, value, callback) => {
      if (value > 150) {
        callback(new Error('年龄输⼊过⼤'));
      } else {
        callback();
      }
    };
    let checkDuplicate = (rule, value, callback) => {
      if (this.form.id) {
        return callback();
      }
      this.$axios.get(this.$httpUrl + "/user/findByNo?no=" + this.form.no).then(res => res.data).then(res => {
        if (res.code != 200) {
          callback()
        } else {
          callback(new Error('该账号已存在'));
        }
      })
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };

    return {
      pageSize:10,
      pageNum:1,
      total:0,
      name: '',
      sex:'' ,
      sexs:[
        {
          value:'1',
          label:'男'
        },{
          value:'0',
          label: '女'
        }
      ],
      form:{
        id:'',
        no:'',
        name:'',
        password:'',
        checkPass:'',
        age:'',
        phone:'',
        sex:'',
        roleId:''
      },
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
          {validator: checkDuplicate, trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        checkPass: [
          {required:true, validator: validatePass2, trigger: 'blur' }
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'blur'},
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'},
          {min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,3}$/, message: '年龄必须为正整数字', trigger: "blur"},
          {validator: checkAge, trigger: 'blur'}
        ],
        phone: [
          {required: true, message: "⼿机号不能为空", trigger: "blur"},
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输⼊正确的⼿机号码", trigger:
                "blur"
          }
        ],
        roleId: [
          {required: true, message: '请选择角色', trigger: 'blur'},
        ]
      }
    }
  },
   methods: {
        save(){
          this.$refs.form.validate((valid) => {
            if (valid) {
              if (this.form.id) {
                this.doMod();
              } else {
                this.doSave();
                this.$message({
                  showClose: true,
                  message: '注册成功，3秒后重定向到登录页面....',
                  type: 'success'
                });
                setTimeout(() => {
                  this.$router.replace("/")
                }, 3000);//遮罩1s

              }

            } else {
              console.log('error submit!!');
              this.$message({
                showClose: true,
                message: '注册失败，请检查表单信息是否正确',
                type: 'error'
              });
              return false;
            }
          });

        },
        doSave() {
          this.$axios.post(this.$httpUrl + '/user/save', this.form).then(res => res.data).then(res => {
            console.log(res)
            if (res.code == 200) {
              this.$notify({
                title: '成功',
                message: '注册成功',
                type: 'success'
              });

              this.loadPost()
            } else {
              this.$notify.error({
                title: '错误',
                message: '注册失败',
                type: 'error'
              });
            }

          })
        },
        doMod() {
          this.$axios.post(this.$httpUrl + '/user/update', this.form).then(res => res.data).then(res => {
            console.log(res)
            if (res.code == 200) {
              this.$notify({
                title: '成功',
                message: '编辑成功',
                type: 'success'
              });

              this.loadPost()
            } else {
              this.$notify.error({
                title: '错误',
                message: '编辑失败',
                type: 'error'
              });
            }

          })
        },
        loadPost(){
          this.$axios.post(this.$httpUrl+'/user/listPageC1',{
            pageSize:this.pageSize,
            pageNum:this.pageNum,
            param:{
              name:this.name,
              sex:this.sex,
              roleId:'0'
            }
          }).then(res=>res.data).then(res=>{

            console.log(res)
            if(res.code==200){

              this.tableData=res.data
              this.total=res.total
              console.log(name)

            }else {

              alert('获取数据失败')
            }

          })

        },
        resetForm() {
          this.$nextTick(()=>{
            this.$refs.form.resetFields();
          })
        },
       replaceLogin(){
          this.$router.replace("/")
       }
      }
}
</script>

<template>
  <div class="registerBody">
    <div class="registerDiv">
      <div class="register-content">
        <h1 class="register-title">WMS仓库管理系统</h1>
        <h3 class="register-title">用户注册</h3>
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
          <el-form-item label="账号" prop="no">
            <el-col :span="20">
              <el-input v-model="form.no"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="姓名" prop="name">
            <el-col :span="20">
              <el-input v-model="form.name"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-col :span="20">
              <el-input show-password v-model="form.password"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="确认密码" prop="checkPass">
            <el-col :span="20">
              <el-input show-password v-model="form.checkPass"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="form.sex">
              <el-radio label="1">男</el-radio>
              <el-radio label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="年龄" prop="age">
            <el-col :span="20">
              <el-input v-model="form.age"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="电话" prop="phone">
            <el-col :span="20">
              <el-input v-model="form.phone"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="角色" prop="roleId">
            <el-col :span="10">
              <el-radio-group v-model="form.roleId">
                <el-radio label="0" style="margin-top: 8px">超级管理员</el-radio>
                <el-radio style="margin-bottom: 8px;margin-top: 8px" label="1">管理员</el-radio>
                <el-radio label="2">普通账号</el-radio>
              </el-radio-group>
            </el-col>
          </el-form-item>
        <el-form-item>
          <el-button @click="replaceLogin" type="info" plain>登 录</el-button>
          <el-button @click="resetForm" plain>重 置</el-button>
          <el-button type="primary" @click="save">注 册</el-button>
        </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.registerBody{
  position: absolute;
  width: 100%;
  height: 100%;
  background-image: url("bg.jpg") ;
  background-size:cover;
}
.registerDiv{
  position: absolute;
  top: 30%;
  left: 50%;
  margin-top: -180px;
  margin-left: -225px;
  width: 440px;
  height: 700px;
  background: #fff;
  border-radius: 5%;
}
.register-title{
  margin: 20px 0;
  text-align: center;
}

.register-content{
  width: 400px;
  height: 250px;
  position: relative;
  top: 25px;
  left: 25px;
}
</style>