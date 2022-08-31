<template>
<div>
  <div class="appointment">
  <div class="process">
    <el-steps :active="active" finish-status="success">
      <el-step title="选择座位"></el-step>
      <el-step title="选择时间"></el-step>
      <el-step title="完成"></el-step>
    </el-steps>

    <el-button style="margin-top: 12px;" @click="next()" v-model="buttonText">{{buttonText}}</el-button>
  </div>
<!-- <router-view></router-view>-->
  <Appointment1 ref="appointment1" v-show="appoint1Visible"></Appointment1>
  <Appointment2 ref="appointment2" v-show="appoint2Visible"></Appointment2>
    <Appointment3 ref="appointment3" v-show="appoint3Visible"></Appointment3>

  </div>
</div>
</template>

<script>
import Appointment1 from "./Appointment1";
import Appointment2 from "./Appointment2";
import Appointment3 from "./Appointment3";

export default {
  components: {Appointment2, Appointment1,Appointment3},
  name: "Appointment",
  data() {
    return{
      active: 0,
      appoint1Visible: true,
      appoint2Visible: false,
      appoint3Visible: false,
      buttonText: '下一步'
    }

  },
  methods: {
    async next() {
      let app='appointment'+(this.active+1);
      this.$refs[app].myValidate(async ()=>{
          this.active++;
          // top.location="/appointment"+this.active;
          // console.log(this.active)
          switch (this.active){
            case 1:this.appoint1Visible=false;this.appoint2Visible=true;break;
            case 2:this.appoint2Visible=false;this.appoint3Visible=true;this.buttonText='提交';break;
            case 3:this.buttonText='下一步';this.appoint3Visible=false;this.appoint1Visible=true;break;
          }
          if (this.active > 2){
            this.active = 0;
            await location.reload();
            await this.$notify({
              title: '预约成功',
              message: '可前往“记录”查看!'
            });
          }
      })
    }
  }
}
</script>

<style scoped>
.process{
  position: absolute;
  width: 80%;
  left: 9%;
  z-index: 1;
  top: 62%;
}

.process el-steps{
  position: relative;
  width: 80%;
}

.appointment{
  position: relative;
  z-index: 1;
  background-color: aliceblue;
  /* background-image: linear-gradient(
160deg, #5eaedc 0%, #80D0C7 100%); */
  left: 3%;
  top: 9%;
  width: 90vw;
  height: 100vh;
}
</style>