<script>


import SelectUser from "@/components/user/SelectUser.vue";

export default {
  name: "GoodManger",
  components: {SelectUser},
  data() {//默认值
    let checkCount = (rule, value, callback) => {
      if(value>9999){
        callback(new Error('数量输⼊过⼤'));
      }else{
        callback();
      }
    };
    return {
      user:JSON.parse(sessionStorage.getItem('CurUser')),
      tableData: [],
      goodtypeData:[],
      storageData:[],
      pageSize:10,
      pageNum:1,
      total:0,
      name: '',
      storage: '',
      goodType: '',
      dialogVisible:false,
      dialogVisibleMod:false,
      dialogVisiblePutin:false,
      dialogVisiblePutout:false,
      innerVisible:false,
      currentRow:{},
      tempUser:{},
      form:{
        id:'',
        name:'',
        storage:'',
        goodType:'',
        count:'',
        remark:''
      },
      formIn:{
        good:'',
        goodname:'',
        count:'',
        userId:'',
        username:'',
        adminId:'',
        remark: '',
        action:'1'
      },
      rulesIn:{

      },
      rules: {

        name: [
          {required: true, message: '请输入产品', trigger: 'blur'},
        ],
        count: [
          {required: true, message: '请输⼊数量', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,4}$/,message: '数量必须为正整数字',trigger: "blur"},
          {validator:checkCount,trigger: 'blur'}
        ],

      },
      loading:false
    }
  },
  methods:{
    formatStorage(row){
      let temp = this.storageData.find(item=>{
        return item.id == row.storage
      })
      return temp && temp.name
    },
    formatGoodType(row){
      let temp = this.goodtypeData.find(item=>{
        return item.id == row.goodType
      })
      return temp && temp.name
    },
    resetForm() {
      this.$refs.form.resetFields();
    },
    resetPutinForm(){
      this.$refs.formIn.resetFields();
    },
    add(){
      this.dialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
        this.form.id=''
      })
    },
    putin(){
     if (this.currentRow.id){
       this.dialogVisiblePutin = true
       this.$nextTick(()=>{
         this.resetPutinForm()

         this.formIn.goodname = this.currentRow.name
         this.formIn.good = this.currentRow.id
         this.formIn.adminId = this.user.id
         this.formIn.action='1'
       })
     }else {
       this.$message.error('请选择要入库的产品');
       return;
     }

    },
    putout(){
      if (this.currentRow.id){
        this.dialogVisiblePutout = true
        this.$nextTick(()=>{
          this.resetPutinForm()

          this.formIn.goodname = this.currentRow.name
          this.formIn.good = this.currentRow.id
          this.formIn.adminId = this.user.id
          this.formIn.action='2'
        })
      }else {
        this.$message.error('请选择要出库的产品');
        return;
      }
    },
    doSave(){
      this.$axios.post(this.$httpUrl+'/good/save',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.$notify({
            title: '成功',
            message: '新增成功',
            type: 'success'
          });
          this.dialogVisible =false
          this.loadPost()
          this.resetForm()
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
      this.$axios.post(this.$httpUrl+'/good/update',this.form).then(res=>res.data).then(res=>{
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
        this.form.name = row.name
        this.form.storage = row.storage
        this.form.goodType = row.goodType
        this.form.count = row.count
        this.form.remark = row.remark
      })

    },
    del(id){
      this.$axios.get(this.$httpUrl+'/good/del?id='+id).then(res=>res.data).then(res=>{
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
    doPutIn(){
      this.$axios.post(this.$httpUrl+'/record/save',this.formIn).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.$notify({
            title: '成功',
            message: '入库成功',
            type: 'success'
          });
          this.dialogVisiblePutin =false
          this.loadPost()
          this.resetPutinForm()
        }else {
          console.log("error")
          this.$notify.error({
            title: '错误',
            message: '入库失败',
            type:'error'
          });
          return false;
        }

      })
    },
    doPutOut(){
      this.$axios.post(this.$httpUrl+'/record/save',this.formIn).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.$notify({
            title: '成功',
            message: '出库成功',
            type: 'success'
          });
          this.dialogVisiblePutout =false
          this.loadPost()
          this.resetPutinForm()
        }else {
          this.$notify.error({
            title: '错误',
            message: '出库失败',
            type:'error'
          });
          return false;
        }

      })
    },
    selectUser(){
      this.innerVisible = true
    },
    confirmUser(){
      this.formIn.username =this.tempUser.name
      this.formIn.userId =this.tempUser.id
      this.innerVisible = false
    },
    doSelectUser(val){
      console.log(val)
      this.tempUser = val
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
    handleSelectChange(val) {
      this.currentRow = val;
    },
    resetParam(){
      this.name=''
      this.storage=''
      this.goodType=''

    },
    loadPost(){
      this.loading = true;
      this.$axios.post(this.$httpUrl+'/good/listPage',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          name:this.name,
          goodType:this.goodType+'',
          storage: this.storage+'',
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          setTimeout(() => {
            this.loading = false;
          }, 1000);//遮罩1s
          this.tableData=res.data
          this.total=res.total
          console.log(name)
        }else {
          alert('获取数据失败')
        }

      })
    },
    loadStorage(){
      this.$axios.get(this.$httpUrl+'/storage/list').then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.storageData=res.data
          console.log(name)
        }else {
          alert('获取数据失败')
        }

      })
    },
    loadGoodType(){
      this.$axios.get(this.$httpUrl+'/goodtype/list').then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.goodtypeData=res.data
          console.log(name)
        }else {
          alert('获取数据失败')
        }

      })
    }

  },
  beforeMount() {
    //this.loadGet();
    this.loadStorage()
    this.loadGoodType()
    this.loadPost()
  }
}
</script>

<template>
  <div v-loading.lock="loading">
    <div style="margin-bottom: 8px; margin-top: 8px;margin-left: 8px;text-align: center">
      <el-input v-model="name" placeholder="输入要查询的产品" suffix-icon="el-icon-search" style=" width:200px"></el-input>
      <el-select style="margin-left: 8px" v-model="storage" placeholder="请选择仓库">
        <el-option
            v-for="item in storageData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-select style="margin-left: 8px" v-model="goodType" placeholder="请选择产品分类">
        <el-option
            v-for="item in goodtypeData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-button plain type="primary" style="margin-left: 8px" @click="loadPost">查询</el-button>
      <el-button plain type="info" @click="resetParam">重置</el-button>
      <el-button plain type="success" style="margin-left: 8px" @click="add" v-if="user.roleId!=2">新增</el-button>
      <el-button plain type="primary" style="margin-left: 8px" @click="putin" v-if="user.roleId!=2">入库</el-button>
      <el-button plain type="primary" style="margin-left: 8px" @click="putout" v-if="user.roleId!=2">出库</el-button>

    </div>
    <el-table style="width: 100%"
              stripe
              border
              :data="tableData"
              :header-cell-style="{background:'#f3f6fd' ,color:'#555'}"
              highlight-current-row
              @current-change="handleSelectChange">

      <el-table-column prop="id" label="序号" width="60">
      </el-table-column>
      <el-table-column prop="name" label="产品" width="150">
      </el-table-column>
      <el-table-column prop="storage" label="仓库" width="200" :formatter="formatStorage">
      </el-table-column>
      <el-table-column prop="goodType" label="产品分类" width="200" :formatter="formatGoodType">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="200">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
      </el-table-column>
      <el-table-column prop="operate" label="操作" width="" v-if="user.roleId!=2">
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


        <el-form-item label="产品" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="仓库" prop="storage">
          <el-col :span="20">
            <el-input v-model="form.storage"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="产品分类" prop="goodType">
          <el-col :span="20">
            <el-input v-model="form.goodType"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form.count"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input  type="textarea"
                       :rows="2"
                       placeholder="请输入内容"
                       v-model="form.remark"></el-input>
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

        <el-form-item label="产品" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="仓库" prop="storage">
          <el-col :span="20">
            <el-input v-model="form.storage"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="产品分类" prop="goodType">
          <el-col :span="20">
            <el-input v-model="form.goodType"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form.count"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input  type="textarea"
                       :rows="2"
                       placeholder="请输入内容"
                       v-model="form.remark"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisibleMod = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>

<!--入库-->
    <el-dialog
        title="入库"
        :visible.sync="dialogVisiblePutin"
        width="30%"
        :before-close="handleClose"
        @close="resetPutinForm">

      <el-dialog
          width="61%"
          title="用户选择"
          :visible.sync="innerVisible"
          append-to-body>
        <SelectUser @doSelectUser="doSelectUser"/>
        <span slot="footer" class="dialog-footer">
             <el-button @click="innerVisible = false">取 消</el-button>
             <el-button type="primary" @click="confirmUser">确 定</el-button>
        </span>
      </el-dialog>

      <el-form ref="formIn" :rules="rulesIn" :model="formIn" label-width="80px">

        <el-form-item label="产品">
          <el-col :span="20">
            <el-input v-model="formIn.goodname" readonly></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="入库人">
          <el-col :span="20">
            <el-input v-model="formIn.username" @click.native="selectUser" readonly></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="formIn.count"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input  type="textarea"
                       :rows="2"
                       placeholder="请输入内容"
                       v-model="formIn.remark"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisiblePutin = false">取 消</el-button>
    <el-button type="primary" @click="doPutIn">确 定</el-button>
  </span>
    </el-dialog>


<!--    出库-->
    <el-dialog
        title="出库"
        :visible.sync="dialogVisiblePutout"
        width="30%"
        :before-close="handleClose"
        @close="resetPutinForm">

      <el-dialog
          width="61%"
          title="用户选择"
          :visible.sync="innerVisible"
          append-to-body>
        <SelectUser @doSelectUser="doSelectUser"/>
        <span slot="footer" class="dialog-footer">
             <el-button @click="innerVisible = false">取 消</el-button>
             <el-button type="primary" @click="confirmUser">确 定</el-button>
        </span>
      </el-dialog>

      <el-form ref="formIn" :rules="rulesIn" :model="formIn" label-width="80px">

        <el-form-item label="产品">
          <el-col :span="20">
            <el-input v-model="formIn.goodname" readonly></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="出库人">
          <el-col :span="20">
            <el-input v-model="formIn.username" @click.native="selectUser" readonly></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="formIn.count"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input  type="textarea"
                       :rows="2"
                       placeholder="请输入内容"
                       v-model="formIn.remark"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisiblePutout = false">取 消</el-button>
    <el-button type="primary" @click="doPutOut">确 定</el-button>
  </span>
    </el-dialog>



  </div>
</template>

<style scoped>

</style>