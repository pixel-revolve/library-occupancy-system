<template>
  <div>
    <div class="login">
      <h1 style="font-size: 37px">管理员登录</h1>
      <el-form style="width: 60%" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="ruleForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click.native="backStudentLogin">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "AdminLogin",
  data() {
    var validateAdministratorName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('账号不能为空'));
      }
      callback();
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      callback();
    };

    return {
      ruleForm: {
        password: '',
        userName: ''
      },
      rules: {
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        userName: [
          { validator: validateAdministratorName, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    async submitForm(formName) {
      const _this=this;
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          let resp= await axios({
                url: '/api/user/login',
                data: _this.ruleForm,
                method: 'post'
          });

          if(resp.data.success) {
            const jwt = resp.headers['authorization']

            // 将jwt提交并覆盖vuex中已有的state中的数据
            this.$store.commit('SET_TOKEN', jwt);
            // 将username保存到state中去
            //this.$store.commit("SET_USERNAME",);

            // localStorage.setItem("isLogin",JSON.stringify(resp.data))
            await _this.$router.replace({path: '/admin'})
          }else {
            console.log(resp.data.message);
            alert(resp.data.message);
          }

        }
      });
    },
    backStudentLogin(){
      this.$router.push({name:'login'})
    }

  }
}
</script>

<style scoped>
.login{
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