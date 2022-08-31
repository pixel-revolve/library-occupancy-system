<template>
  <div>
  <div class="left_div">
    <section class="avatar2">
      <div>
        <img class="avatar_" :src="$store.state.avatar"></img>
      </div>
      <div v-model="userName">{{userName}}</div>
    </section>

    <section class="list">
      <ul class="left_ul">
        <li class="li_1">
          <div></div>
          专业资料管理
        </li>
        <li class="li_2">
          <div></div>
          我的预约
        </li>
        <li class="li_3">
          <div></div>
          账号管理
        </li>
      </ul>
    </section>
    <div class="right_div">
    <router-view></router-view>

    </div>
  </div>


  </div>
</template>

<script>
export default {
  name: "PersonalCenter",
  data(){
    return{
      userName:''
    }
  },
  mounted() {
    this.userName=this.$store.state.username;

  }

}

</script>

<style scoped>



@import "../assets/css/personalHall.css";


</style>