<template>
  <div>
  <div class="total">
    <div class="top">
      <h2>图书馆信息公告</h2>
      <hr>
      <h4 class="text-center">新的公告</h4>
    </div>
    <div class="content">
      <button class="accordion">图书馆卷王争霸赛</button>
      <div class="panel">
        <p>内卷：一种人们在竞争中彼此消耗的状态，类似剧场效应，剧场中一个人站了起来，其他人为了看到演出就必须站得更高，结果是所有人都站了起来，却没有看得更清楚。
          比如10人竞聘1个岗位，每个人都拼命努力，结果是每个人都非常疲劳，但仍只有1人被录取，这个被录取的人就被称为“卷王”。
        </p>
      </div>

      <button class="accordion">图书馆闭馆说明</button>
      <div class="panel">
        <p>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；
          页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。
        </p>
      </div>

      <button class="accordion">冒死揭秘图书馆内幕</button>
      <div class="panel">
        <p>简化流程：设计简洁直观的操作流程；
          清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；
          帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。
        </p>
      </div>

      <button class="accordion">东西落在图书馆怎么办</button>
      <div class="panel">
        <p>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；
          结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。
        </p>
      </div>
    </div>
    <div style="bottom: 0;position: fixed;background-color: #777777;width: 100%">
      <ul class="text-center">
        <li class = "page"><button style="border-radius: 20%;border-width: thin;cursor: pointer;padding-left:15px;padding-right: 15px;padding-top: 8px;padding-bottom: 8px ">&lt;&lt;</button></li>
        <li class = "page"><button style="border-radius: 20%;border-width: thin;cursor: pointer;padding-left:15px;padding-right: 15px;padding-top: 8px;padding-bottom: 8px">&lt;</button></li>
        <li style="display: inline;"><button style="background-color: royalblue;color: #fafafa;border-radius: 20%;border-width: thin;padding-left:15px;padding-right: 15px;padding-top: 8px;padding-bottom: 8px;cursor: pointer;text-decoration: underline">1</button></li>
        <li class="page"><button style="border-radius: 20%;border-width: thin;cursor: pointer;padding-left:15px;padding-right: 15px;padding-top: 8px;padding-bottom: 8px;">&gt;</button></li>
        <li class="page"><button style="border-radius: 20%;border-width: thin;cursor: pointer;padding-left:15px;padding-right: 15px;padding-top: 8px;padding-bottom: 8px;">&gt;&gt;</button></li>
      </ul>
    </div>
  </div>
</div>

</template>

<script>
export default {
  name: "Notice",
  mounted() {
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
      acc[i].addEventListener("click", function () {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.maxHeight) {
          panel.style.maxHeight = null;
        } else {
          panel.style.maxHeight = panel.scrollHeight + "px";
        }
      });
    }

  }

}
// $(document).ready(function () {
// var acc = document.getElementsByClassName("accordion");
// var i;
//
// for (i = 0; i < acc.length; i++) {
//   acc[i].addEventListener("click", function () {
//     this.classList.toggle("active");
//     var panel = this.nextElementSibling;
//     if (panel.style.maxHeight) {
//       panel.style.maxHeight = null;
//     } else {
//       panel.style.maxHeight = panel.scrollHeight + "px";
//     }
//   });
// }
// })


</script>

<style scoped>
@import "../assets/css/N.css";

body{
  background-color: #00e3ff0d;
}
.total{
  position: relative;
  z-index: 1;
  background-color: aliceblue;
  height: 100vh;
  width: 90vw;
  left: 5%;
}
</style>