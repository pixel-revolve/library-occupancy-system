<template>
  <div>
    <div class="register">
      <h1 style="font-size: 37px">注册</h1>
  <el-form style="width: 60%" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="学号" prop="userName">
      <el-input v-model.number="ruleForm.userName"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
 data() {
  var validateStudentId = (rule, value, callback) => {
    if (value==='') {
      return callback(new Error('学号不能为空'));
    }
    callback();
  };
  var validatePass = (rule, value, callback) => {
    if (value === '') {
      callback(new Error('请输入密码'));
    } else {
      if (this.ruleForm.checkPass !== '') {
        this.$refs.ruleForm.validateField('checkPass');
      }
      callback();
    }
  };
  var validatePass2 = (rule, value, callback) => {
    if (value === '') {
      callback(new Error('请再次输入密码'));
    } else if (value !== this.ruleForm.password) {
      callback(new Error('两次输入密码不一致!'));
    } else {
      callback();
    }
  };
  return {
    ruleForm: {
      password: '',
      checkPass: '',
      userName: ''
    },
    rules: {
      password: [
        { validator: validatePass, trigger: 'blur' }
      ],
      checkPass: [
        { validator: validatePass2, trigger: 'blur' }
      ],
      userName: [
        { validator: validateStudentId, trigger: 'blur' }
      ]
    }
  };
},
methods: {
  async submitForm(formName) {
    const _this=this;
     this.$refs[formName].validate(async (valid) => {
      if (valid) {
        let resp= await axios(
            {
              url: '/api/register',
              data: _this.ruleForm,
              method: 'post'
            }
        )
        //console.log(resp);

        if(resp.data.success){
          alert('注册成功!');
          this.$router.push({name:'login'});
        }else{
          //console.log(resp.data.message);
          alert(resp.data.message);
        }

      } else {
        console.log('注册失败!');
        return false;
      }

    });
  },
  resetForm(formName) {
    this.$refs[formName].resetFields();
  }
}
}
</script>

<style scoped>
.register{
  position: relative;
  z-index: 1;
  background-color: aliceblue;
  width: 90vw;
  height: 100vh;
  top: 10%;
  left: 3%;
}
.el-form{
  position: absolute;
  top: 20%;
  left: 13vw;
}

</style>