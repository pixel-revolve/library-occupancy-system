<template>
  <div>
<div class="appointment1" ref="appointment1">
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="选择楼层" prop="floor">
      <el-select v-model="ruleForm.floor" placeholder="请选择预约楼层">
        <el-option v-for="(floor,index) in floors" :value="floor" @click.native ="handleSelect(index)"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="空余座位" prop="seat">
      <el-select v-model="ruleForm.seat" placeholder="请先选择座位">
        <el-option v-for="(seat,index) in seats[activeFloor]" :value="'BS100'+seat"></el-option>
      </el-select>
    </el-form-item>


  </el-form>

  <div class="infinite-list-wrapper" style="overflow:auto" v-if="!!ruleForm.floor">
    <ul
        class="list2"
        v-infinite-scroll="load"
        infinite-scroll-disabled="disabled">
      <li v-for="i in count" class="list-item" :class="{active:i-1===seatToSeatId,noActive:i-1!==seatToSeatId}">{{'BS100'+ (i-1) }}</li>
    </ul>
    <p v-if="loading">加载中...</p>
    <p v-if="noMore">没有更多了</p>
  </div>

</div>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "Appointment1",
  data(){
      return {
        ruleForm: {
          floor:'',
          seat: ''
        },
        rules: {
          floor: [
            { required: true, message: '请选择楼层', trigger: 'change' }
          ],
          seat: [
            { required: true, message: '请选择座位', trigger: 'change' }
          ]

        },
        seats:[

        ],
        floors: [
            '一楼',
            '二楼',
            '三楼',
            '四楼',
            '五楼',
            '六楼',
            '七楼'
        ],
        activeFloor: null,
        count: 10,
        loading: false
      }

  },
  methods: {
    async handleSelect(index){
      this.activeFloor=index;
    },
    submitForm(formName) {
      let flag=false;
      this.$refs[formName].validate( (valid) => {
        if (valid) {
          let tmp=this.ruleForm.seat;
          let start=tmp.search('BS100')+5;
          let seatId=0;
          while(start!==tmp.length){
            seatId*=10;
            seatId+=parseInt(tmp[start]);
            start++;
          }
          console.log(seatId);
          let floor=this.ruleForm.floor;
          axios({
            url:'/api/user/getSeatBySeatIdAndFloor/'+floor+'/'+seatId,
            method:'get',
            headers: {
              token: this.$store.state.token
            }
          }).then(resp=>{
            let id=resp.data.data.id;
            let seat={}
            seat['id']=id;
            this.$store.commit('SET_RESERVATIONTABLE',['seat',seat])
          })
          //console.log(this.$store.state.reservationTable)
          alert('成功!');
          flag=true;
        } else {
          console.log('失败!!');
          flag=false;
        }
      });
      return flag;
    },
    async myValidate(callback){
      let flag=null;
      flag=this.submitForm('ruleForm');
      if(flag==true){
        callback();
      }
    },
    load () {
      this.loading = true
      setTimeout(() => {
        this.count += 2
        this.loading = false
      }, 2000)
    }
  },
  computed: {
    noMore () {
      return this.count >= 20
    },
    disabled () {
      return this.loading || this.noMore
    },
    seatToSeatId(){
      let tmp=this.ruleForm.seat;
      if(tmp==='')
        return -1;
      let start=tmp.search('BS100')+5;
      let seatId=0;
      while(start!==tmp.length){
        seatId*=10;
        seatId+=parseInt(tmp[start]);
        start++;
      }
      return seatId;
    }
  },
  async mounted() {
    this.ruleForm.floor='';
    this.ruleForm.seat='';
    const resp = await axios({
      url:'/api/user/getSeatList',
      method: 'get',
      headers: {
          token: this.$store.state.token
      }
    });
    //console.log(resp);
    let len=7;
    let array=new Array(len);//创建7楼的表
    for (let i = 0; i < len; i++) {//初始化二维数组
      array[i]=[];
    }
    let seatData=resp.data.data;
    for (const seatDataKey in seatData) {
      switch (seatData[seatDataKey].floor){
        case'一楼': if(!array[0].includes(seatData[seatDataKey].seatId))array[0].push(seatData[seatDataKey].seatId);break;
        case'二楼': if(!array[1].includes(seatData[seatDataKey].seatId))array[1].push(seatData[seatDataKey].seatId);break;
        case'三楼':if(!array[2].includes(seatData[seatDataKey].seatId))array[2].push(seatData[seatDataKey].seatId);break;
        case'四楼':if(!array[3].includes(seatData[seatDataKey].seatId))array[3].push(seatData[seatDataKey].seatId);break;
        case'五楼':if(!array[4].includes(seatData[seatDataKey].seatId))array[4].push(seatData[seatDataKey].seatId);break;
        case'六楼':if(!array[5].includes(seatData[seatDataKey].seatId))array[5].push(seatData[seatDataKey].seatId);break;
        case'七楼':if(!array[6].includes(seatData[seatDataKey].seatId))array[6].push(seatData[seatDataKey].seatId);break;
      }
    }
    this.seats=array;
    // console.log(this.seats);
  }
}

</script>

<style scoped>
.infinite-list-wrapper{
  z-index: 1;
  height: 29vh;
  width: 90vw;
}
.infinite-list-wrapper ul{
  background-color: #f2f6fc;
  padding: 1vw;
  margin: 1vw;
}
.infinite-list-wrapper li{
  margin: 1vw;
}
.active{
  background-color: #f7ff3a;
}
.noActive{
  background-color: #6cb8cf;
}

</style>