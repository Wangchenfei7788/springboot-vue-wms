<script>


export default {
  name: "StorageManger",
  data() {//默认值
    return {
      tableData: [],
      pageSize:15,
      pageNum:1,
      total:0,
      name: '',
      dialogVisible:false,
      dialogVisibleMod:false,
      form:{
        id:'',
        name:'',
        remark:''

      },
      rules: {

        name: [
          {required: true, message: '请输入仓库名', trigger: 'blur'},
        ],


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
      this.$axios.post(this.$httpUrl+'/storage/save',this.form).then(res=>res.data).then(res=>{
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
      this.$axios.post(this.$httpUrl+'/storage/update',this.form).then(res=>res.data).then(res=>{
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
        this.form.name =row.name
        this.form.remark = row.remark
      })

    },
    del(id){


      this.$axios.get(this.$httpUrl+'/storage/del?id='+id).then(res=>res.data).then(res=>{
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
    /* loadGet(){
      this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    }, */
    resetParam(){
      this.name=''

    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/storage/listPage',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          name:this.name,
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
    }

  },
  beforeMount() {
    //this.loadGet();
    this.loadPost()
  }
}
</script>

<template>
  <div>
    <div style="margin-bottom: 8px; margin-top: 8px;margin-left: 8px;text-align: center">
      <el-input v-model="name" placeholder="输入要查询的仓库名" suffix-icon="el-icon-search" style=" width:200px"></el-input>
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
      <el-table-column prop="name" label="仓库名" width="200">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
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


        <el-form-item label="仓库名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input v-model="form.remark"></el-input>
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

        <el-form-item label="仓库名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input  v-model="form.remark"></el-input>
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