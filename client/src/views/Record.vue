<template>
  <div>
    <div class="table">
  <el-table
      :data="tableData"
      stripe
      style="width: 83%"
      >
    <el-table-column
        prop="date"
        label="预约日期"
        width="180">
    </el-table-column>
    <el-table-column
        prop="name"
        label="姓名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="address"
        label="预约座位"
        width="180">
    </el-table-column>
    <el-table-column
        prop="time"
        label="预约时间段"
        width="400">
    </el-table-column>
  </el-table>
      <el-pagination
          layout="prev, pager, next"
          :page-size="8"
          :total="total"
          @current-change="page">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Record",
  data() {
    return{
      total: 0,
      tableData: []
    }
  },
  methods: {
    async page(currentPage){

      // let token=window.localStorage.getItem('token');
      let resp=await axios({
        url: '/api/user/getReservationList/'+currentPage+'/8',
        method: 'get',
        headers: {
          token: this.$store.state.token
        }
      });

      console.log(resp);
      this.total=resp.data.totalCount;
      let respData=resp.data.dataCurrentPage;
      let tableDataTem=[];

        for (let i = 0; i < respData.length; i++) {
          let name=respData[i].user.userName;
          let address=respData[i].seat.floor+' '+'BS100'+respData[i].seat.seatId;
          let date=respData[i].gmtCreated;
          let tableDataMember={};
          tableDataMember.name=name;
          tableDataMember.address=address;
          tableDataMember.date=date;
          let time=date+' ~ '+respData[i].time;
          tableDataMember.time=time;
         tableDataTem.push(tableDataMember);
        }
        this.tableData=tableDataTem;
    }

  },
  async mounted() {
    //console.log(this.$store.state.token)
    // let token=window.localStorage.getItem('token');
    let resp=await axios({
      url: '/api/user/getReservationList/1/8',
      method: 'get',
      headers: {
        token: this.$store.state.token
      }
    });

    //console.log(resp);
    this.total=resp.data.totalCount;
    //console.log(this.total);
    let respData=resp.data.dataCurrentPage;
    let tableDataTem=[];

    for (let i = 0; i < respData.length; i++) {
      let name=respData[i].user.userName;
      let address=respData[i].seat.floor+' '+'BS100'+respData[i].seat.seatId;
      let date=respData[i].gmtCreated;
      let tableDataMember={};
      tableDataMember.name=name;
      tableDataMember.address=address;
      tableDataMember.date=date;
      let time=date+' ~ '+respData[i].time;
      tableDataMember.time=time;
      tableDataTem.push(tableDataMember);
    }
    this.tableData=tableDataTem;
  },

  created(){




  }

}
</script>

<style scoped>
.table{
  position: relative;
  z-index: 1;
  background-color: aliceblue;
  left: 3%;
  top: 9%;
  width: 90vw;
  height: 100vh;
}
.el-table--fit {
  border-right: 0;
  border-bottom: 0;
  left: 6%;
}


</style>