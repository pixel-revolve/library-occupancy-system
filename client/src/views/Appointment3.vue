<template>
  <h1>完成</h1>
</template>

<script>
import axios from "axios";

export default {
  name: "Appointment3",
  methods: {
    myValidate(callback){
      //在这里进行提交
      let user={}
      user['id']=this.$store.state.id;
      this.$store.commit('SET_RESERVATIONTABLE',['user',user])
      // console.log(this.$store.state.reservationTable)
      axios({
        url:'/api/user/addReservation',
        method: 'post',
        headers: {
          token: this.$store.state.token
        },
        data: this.$store.state.reservationTable
      }).then(resp=>{
        // console.log(resp)
      })
      //然后在这里进行删除
      this.$store.commit('resetReservation')

      callback();
    }
  }
}


</script>

<style scoped>

</style>