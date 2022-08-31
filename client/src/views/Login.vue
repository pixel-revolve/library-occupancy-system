<template>
<div>

  <div class="login">
    <h1 style="font-size: 37px">学生登录</h1>
  <el-form style="width: 60%" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="学号" prop="userName">
      <el-input v-model="ruleForm.userName"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click.native="adminLogin">管理员登录</el-button>
    </el-form-item>
  </el-form>
  </div>
</div>

</template>

<script>
// import axios from "../assets/js/axios";

import axios from "axios";

export default {
  name: "Login",
  data() {
    var validateUserName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('学号不能为空'));
      }
      callback();
    };
    var validatePassword = (rule, value, callback) => {
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
          { validator: validatePassword, trigger: 'blur' }
        ],
        userName: [
          { validator: validateUserName, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    async submitForm(formName) {
      const _this=this;
      this.$refs[formName].validate(async (valid) => {
        if (valid) {

            let resp = await axios(
                {
                  url: '/api/user/login',
                  data: _this.ruleForm,
                  method: 'post'
                }
            )

          alert(resp.data.message)
          console.log(resp.data.message)
          if(resp.data!=null){
            const jwt = resp.headers['authorization']

            // 将jwt提交并覆盖vuex中已有的state中的数据
            this.$store.commit('SET_TOKEN', jwt);
            // 将username保存到state中去
            //this.$store.commit("SET_USERNAME",);
            // localStorage.setItem("isLogin",JSON.stringify(resp.data))
            await _this.$router.replace({path:'/'})

          }

          // if(resp.data.success) {
          //   alert('登录成功!');
          //   //对它进行操作
          //   //top.location='/';
          // }else{
          //   console.log(resp.data.message);
          //   alert(resp.data.message);
          // }
        }
      });
    },
    adminLogin(){
      this.$router.push("adminLogin")
    }

  }
}
</script>

<style scoped>
.login{
  position: relative;
  z-index: 1;
  background-color: aliceblue;
  width: 91vw;
  /*height: calc(100vh - 60px);*/
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