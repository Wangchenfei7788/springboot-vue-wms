<script>


export default {
  name: "RecordManger",
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
      pageSize:15,
      pageNum:1,
      total:0,
      name: '',
      storage: '',
      goodType: '',

      dialogVisible:false,
      dialogVisibleMod:false,
      form:{
        id:'',
        name:'',
        storage:'',
        goodType:'',
        count:'',
        remark:''

      },
      loading:false
    }
  },
  methods:{
    formatStorage(row){
      let temp = this.storageData.find(item=>{
        return item.id = row.storage
      })
      return temp && temp.name
    },
    formatGoodType(row){
      let temp = this.goodtypeData.find(item=>{
        return item.id = row.goodType
      })
      return temp && temp.name
    },
    resetForm() {
      this.$refs.form.resetFields();
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
      this.storage=''
      this.goodType=''

    },
    loadPost(){
      this.loading = true;

      this.$axios.post(this.$httpUrl+'/record/listPage',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          name:this.name,
          goodType:this.goodType+'',
          storage: this.storage+'',
          roleId:this.user.roleId+'',
          userId:this.user.id+'',

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
      <el-table-column prop="goodName" label="产品" width="150">
      </el-table-column>
      <el-table-column prop="storagename" label="仓库" width="150">
      </el-table-column>
      <el-table-column prop="goodtypename" label="产品分类" width="150">
      </el-table-column>
      <el-table-column prop="userName" label="入/出库人" width="200">
      </el-table-column>
      <el-table-column prop="adminname" label="操作人" width="200">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="100">
      </el-table-column>
      <el-table-column prop="createtime" label="操作时间" width="200">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
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
  </div>
</template>

<style scoped>

</style>