<template>
  <div>
  <div class="total">
    <div class="top">
      <h2>帮助</h2>
      <hr>
      <h4 class="text-center">管理员邮箱 ThisIsExample@example.com</h4>
    </div>
    <br>
    <div class="help">
      <div class="icon-bar">
        <a class="active" href="#"><i class="fa fa-home"></i></a>
        <a href="#"><i class="fa fa-search"></i></a>
        <a href="#"><i class="fa fa-envelope"></i></a>
        <a href="#"><i class="fa fa-globe"></i></a>
        <a href="#"><i class="fa fa-trash"></i></a>
      </div>
      <p class="context">CPU跑分：可以为你的CPU进行跑个分，并且通过他人的跑分数据让您了解您的手机的大致性能。

        内存测试：通过暴力填充系统运行内存的方式来判别真正运行内存。

        磁盘测试：通过填充磁盘的方式测试手机真实储存空间。

        在线排行榜：您的跑分数据将会被上传到软件数据库中，与他人共享。您也可以通过查看您在排行榜中的位置来了解您的手机的大致性能

        还有更多实用功能等你来发现！</p>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "Help"
}
</script>

<style scoped>
.total{
  position: relative;
  z-index: 1;
  width: 90vw;
  left: 4%;
  height: 100vh;
  background-color: aliceblue;
}

@import "../assets/css/N.css";

</style>