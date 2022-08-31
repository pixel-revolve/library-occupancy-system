<template>
  <div>
    <div class="appointment2">
      <span></span>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="开始时间" required>
        <el-col :span="11">
          <el-form-item prop="startDate1">
            <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="ruleForm.startDate1" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item prop="startDate2">
            <el-time-picker placeholder="选择时间" v-model="ruleForm.startDate2" value-format="HH:mm:ss" style="width: 100%;"></el-time-picker>
          </el-form-item>
        </el-col>
      </el-form-item>

        <el-form-item label="结束时间" required>
          <el-col :span="11">
            <el-form-item prop="endDate1">
              <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.endDate1" value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="endDate2">
              <el-time-picker placeholder="选择时间" v-model="ruleForm.endDate2" style="width: 100%;" value-format="HH:mm:ss"></el-time-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
      </el-form>
    </div>


  </div>


</template>

<script>
export default {
  name: "Appointment2.vue",
  data() {
    return {
      ruleForm: {
        startDate1: '',
        startDate2: '',
        endDate1: '',
        endDate2: ''
      },
      rules: {

        startDate1: [
          { required: true, message: '请选择日期', trigger: 'change' }
        ],
        startDate2: [
          { required: true, message: '请选择时间', trigger: 'change' }
        ],
        endDate1: [
          { required: true, message: '请选择日期', trigger: 'change' }
        ],
        endDate2: [
          { required: true, message: '请选择时间', trigger: 'change' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      let flag=false;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let gmtCreated=this.ruleForm.startDate1+' '+this.ruleForm.startDate2;
          let time=this.ruleForm.endDate1+' '+this.ruleForm.endDate2;
          this.$store.commit('SET_RESERVATIONTABLE',['gmtCreated',gmtCreated]);
          this.$store.commit('SET_RESERVATIONTABLE',['time',time]);
          //console.log(this.$store.state.reservationTable)
          alert('成功!');
          flag=true;
        } else {
          console.log('失败!');
          flag=false;
        }
      });
      return flag;
    },
    myValidate(callback) {
      let flag=null;
      flag=this.submitForm('ruleForm');
      if(flag==true){
        callback();
      }
    }
  },
  mounted() {
    this.ruleForm.startDate1= ''
    this.ruleForm.startDate2= ''
    this.ruleForm.endDate1= ''
    this.ruleForm.endDate2= ''
  }

}

</script>

<style scoped>

</style>