<template>
  <div>
    <div class="head1">
      <div v-model="username" class="userTitle">
      <p class="text-center h2 opensans-base" id="p0">欢迎回来，{{username}}</p>
        <el-divider></el-divider>
      </div>


      <h3 id="p1">距离考研还剩: </h3>
      <div class="calendar">
        <!-- main codes start -->
        <div class="signboard outer">
          <div class="signboard front inner anim04c">
            <li class="year anim04c">
              <span></span>
            </li>
            <ul class="calendarMain anim04c">
              <li class="month anim04c">
                <span></span>
              </li>
              <li class="date anim04c">
                <span></span>
              </li>
              <li class="day anim04c">
                <span></span>
              </li>
            </ul>
            <li class="clock minute anim04c">
              <span></span>
            </li>
            <li class="calendarNormal date2 anim04c">
              <span></span>
            </li>
          </div>
          <div class="signboard left inner anim04c">
            <li class="clock hour anim04c">
              <span></span>
            </li>
            <li class="calendarNormal day2 anim04c">
              <span></span>
            </li>
          </div>
          <div class="signboard right inner anim04c">
            <li class="clock second anim04c">
              <span></span>
            </li>
            <li class="calendarNormal month2 anim04c">
              <span></span>
            </li>
          </div>
        </div>
        <!-- main codes end -->
      </div>
      <p id="p2">100天！</p>



    <div class="libraryState">
    <dl class="list maki">
      <dt>图书馆状态</dt>
      <dd><a href="#">当前在馆人数: 145</a></dd>
      <dd><a href="#">总座位数: 250</a></dd>
      <dd><a href="#">空余座位数: 99</a></dd>
      <dd><a href="#">管理员状态: active</a></dd>
    </dl>
    </div>

    <h2 id="p3">当前图书馆饱和度</h2>
    <el-progress :percentage="145/255*100" :format="format"></el-progress>

      <div class="hallNotice">
        <h3>新的公告</h3>
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="图书馆卷王争霸赛" name="1">
            <div>内卷：一种人们在竞争中彼此消耗的状态，类似剧场效应，剧场中一个人站了起来，其他人为了看到演出就必须站得更高，结果是所有人都站了起来，却没有看得更清楚。</div>
            <div>比如10人竞聘1个岗位，每个人都拼命努力，结果是每个人都非常疲劳，但仍只有1人被录取，这个被录取的人就被称为“卷王”。</div>
          </el-collapse-item>
          <el-collapse-item title="图书馆闭馆说明" name="2">
            <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
            <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
          </el-collapse-item>
          <el-collapse-item title="冒死揭秘图书馆内幕!" name="3">
            <div>简化流程：设计简洁直观的操作流程；</div>
            <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
            <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
          </el-collapse-item>
          <el-collapse-item title="东西落在图书馆怎么办？" name="4">
            <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
            <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
          </el-collapse-item>
        </el-collapse>
      </div>

  </div>

  </div>
</template>

<script>
export default {
  name: "Hall",
  data(){
    return{
        username: '杜宇豪',
        activeName: '1'
    }
  },
  methods: {
    format(percentage) {
      return percentage === 100 ? '满' : `${parseInt(percentage)}%`;
    }
  },
  mounted() {
    this.username=this.$store.state.username;

  }

}
import "../assets/js/libraryDiscription.js"


$(document).ready(function () {

  var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
  var dayNames= [ "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday" ];

  var newDate = new Date();
  newDate.setDate(newDate.getDate());

  setInterval( function() {
    var hours = new Date().getHours();
    $(".hour").html(( hours < 10 ? "0" : "" ) + hours);
    var seconds = new Date().getSeconds();
    $(".second").html(( seconds < 10 ? "0" : "" ) + seconds);
    var minutes = new Date().getMinutes();
    $(".minute").html(( minutes < 10 ? "0" : "" ) + minutes);

    $(".month span,.month2 span").text(monthNames[newDate.getMonth()]);
    $(".date span,.date2 span").text(newDate.getDate());
    $(".day span,.day2 span").text(dayNames[newDate.getDay()]);
    $(".year span").html(newDate.getFullYear());
  }, 1000);



  $(".outer").on({
    mousedown:function(){
      $(".dribbble").css("opacity","1");
    },
    mouseup:function(){
      $(".dribbble").css("opacity","0");
    }
  });

});

/**/


</script>

<style>
.userTitle{
  position: sticky;
  z-index: 1;
}

.el-progress{
  position: relative;
  line-height: 2;
  top: 42vh;
  left: 6%;
  width: 47%;
  z-index: 1;
}

.head1{
  position: relative;
  width: 90vw;
  top: 10%;
  height: 100vh;
  left: 4%;
  background-color: aliceblue;
}

.calendar{
  position: relative;
  right: -23vw;
  top: 11vh;
}

#p1{
  position: relative;
  right: -23vw;
  top: -1vh;
  z-index: 1;
}
#p2{
  position: relative;
  right: -24vw;
  top: 7vh;
  font-size: xx-large;
  z-index: 1;
}
#p3{
  position: absolute;
  top: 70%;
  left: 17%;
  z-index: 1;
}
.libraryState{
  position: absolute;
  top: 12%;
  left: 22vw;
  z-index: 1;
}


.opensans-base, body td {
  font-family: Open Sans,Chinese Quote,-apple-system,BlinkMacSystemFont,Segoe UI,PingFang SC,Hiragino Sans GB,Microsoft YaHei,Helvetica Neue,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji,Segoe UI Symbol;
}

.text-center {
  text-align: center!important;
}
.h2,h2 {
  font-size: 2rem;
}
.h3,h3{
  font-size: 1.6rem;
}

.hallNotice{
  position: relative;
  left: 49vw;
  width: 39vw;
  top: 8vh;
  z-index: 1;
}


@import "../assets/css/calendar.css";
@import "../assets/css/libraryDiscripiton.css";

</style>