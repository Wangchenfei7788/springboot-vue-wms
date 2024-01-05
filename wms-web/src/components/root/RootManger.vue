<script>

export default {
  name: "RootManger",
  data() {//默认值
    let checkAge = (rule, value, callback) => {
      if(value>150){
        callback(new Error('年龄输⼊过⼤'));
      }else{
        callback();
      }
    };
    let checkDuplicate =(rule,value,callback)=>{
      if(this.form.id){
        return callback();
      }
      this.$axios.get(this.$httpUrl+"/user/findByNo?no="+this.form.no).then(res=>res.data).then(res=>{
        if(res.code!=200){
          callback()
        }else{
          callback(new Error('该账号已存在'));
        }
      })
    };
    return {
      tableData: [],
      pageSize:15,
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
      dialogVisible:false,
      dialogVisibleMod:false,
      form:{
        id:'',
        no:'',
        name:'',
        password:'',
        age:'',
        phone:'',
        sex:'',
        roleId:'0'

      },

      loading:false,
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
          {validator:checkDuplicate,trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        sex:[
          {required: true, message: '请选择性别', trigger: 'blur'},
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'},
          {min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,3}$/,message: '年龄必须为正整数字',trigger: "blur"},
          {validator:checkAge,trigger: 'blur'}
        ],
        phone: [
          {required: true,message: "⼿机号不能为空",trigger: "blur"},
          {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输⼊正确的⼿机号码", trigger:
                "blur"}
        ],
        roleId:[
          {required: true, message: '请选择角色', trigger: 'blur'},
        ]

      }
    }
  },
  methods:{
    resetForm() {
      this.$refs.form.resetFields();
    },
    add(){
      this.dialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
      })
    },
    doSave(){
      this.$axios.post(this.$httpUrl+'/user/save',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.$notify({
            title: '成功',
            message: '新增成功',
            type: 'success'
          });
          this.dialogVisible =false
          this.loadPost()
        }else {
          this.$notify.error({
            title: '错误',
            message: '新增失败',
            type:'error'
          });
        }

      })
    },
    doMod(){
      this.$axios.post(this.$httpUrl+'/user/update',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.$notify({
            title: '成功',
            message: '编辑成功',
            type: 'success'
          });
          this.dialogVisibleMod =false
          this.loadPost()
        }else {
          this.$notify.error({
            title: '错误',
            message: '编辑失败',
            type:'error'
          });
        }

      })
    },
    mod(row){
      console.log(row)


      //显示编辑框
      this.dialogVisibleMod= true

      this.$nextTick(()=>{

        //赋值到表单
        this.form.id = row.id
        this.form.no = row.no
        this.form.name =row.name
        this.form.password = row.password
        this.form.sex = row.sex+''
        this.form.age = row.age+''
        this.form.phone = row.phone
        this.form.roleId = row.roleId+''


      })

    },
    del(id){


      this.$axios.get(this.$httpUrl+'/user/del?id='+id).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.$message({

            message: '删除成功',
            type: 'success'
          });

          this.loadPost()
        }else {
          this.$message.error({

            message: '删除失败',
            type:'error'
          });
        }

      })

    },
    save(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.form.id){
            this.doMod();
          }else {
            this.doSave();
          }

        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize=val
      this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum=val
      this.loadPost()
    },
    loadGet(){
      this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    },
    resetParam(){
      this.name=''
      this.sex=''
    },
    loadPost(){

      this.loading = true;
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
          setTimeout(() => {
            this.loading = false;
          }, 1000);

          this.tableData=res.data
          this.total=res.total
          console.log(name)

        }else {

          alert('获取数据失败')
        }

      })

    },

  },
  beforeMount() {
    //this.loadGet();
    this.loadPost()
  }
}
</script>

<template>
  <div v-loading.lock="loading">

    <div style="margin-bottom: 8px; margin-top: 8px;margin-left: 8px;text-align: center">
      <el-input v-model="name" placeholder="输入要查询的姓名" suffix-icon="el-icon-search" style=" width:200px"></el-input>
      <el-select v-model="sex" filterable placeholder="请选择性别" style="margin-left: 8px">
        <el-option
            v-for="item in sexs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button plain type="primary" style="margin-left: 8px" @click="loadPost">查询</el-button>
      <el-button plain type="info" @click="resetParam">重置</el-button>
      <el-button plain type="success" style="margin-left: 8px" @click="add">新增</el-button>
    </div>
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
      <el-table-column prop="name" label="姓名" width="150">
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
      <el-table-column prop="operate" label="操作" width="">
        <template slot-scope="scope">
          <el-button size="small" plain type="primary" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 8px"
          >
            <el-button slot="reference" size="small" plain type="danger">删除</el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="float: right"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="pageNum"
                   :page-sizes="[ 10, 15, 25, 30]"
                   :page-size="pageSize"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total">
    </el-pagination>

    <!--新增弹出窗口-->
    <el-dialog
        title="新增"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
        @close="resetForm">

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
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>

    <!--编辑弹出窗口-->
    <el-dialog
        title="编辑"
        :visible.sync="dialogVisibleMod"
        width="30%"
        :before-close="handleClose"
        @close="resetForm">

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
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisibleMod = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>