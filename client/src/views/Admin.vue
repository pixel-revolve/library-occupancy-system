<template>
<div>
  <div style="margin-top: 15px;text-align: center;background-color: #f1f1f1;margin-left: 15%;margin-right: 15%;">
    <h1 style="display: inline">欢迎您! 尊敬的管理员 dyh !</h1>
  </div>
  <div style="text-align: center;margin-top: 5px;background-color: #f1f1f1;margin-left: 45%;margin-right: 45%">
    <a  href="/" style="text-decoration: none;display: block;color: midnightblue">登出</a>
  </div>
  <div>
    <ul>
      <li><a class="active" href="#yuyue">预约信息</a></li>
      <li><a href="#yonghu">用户管理</a></li>
      <li><a href="#zhuangtai">图书馆状态</a></li>
    </ul>

    <div style="margin-left:30%;padding:1px 16px;margin-top: 103px;">
      <h1>预约请求信息</h1>
    </div>
  </div>
</div>


</template>

<script>
export default {
  name: "Admin"
}
</script>

<style scoped>
body {
  margin: 0;
}

ul {
  list-style-type: none;
  padding: 0;
  top: 15%;
  width: 15%;
  background-color: #f1f1f1;
  margin-left: 5%;
  position: fixed;
  height: 60%;
  margin-top: 80px;
}

li a {
  display: block;
  color: #000;
  padding: 8px 16px;
  text-decoration: none;
}

li a.active {
  background-color: #4CAF50;
  color: white;
}

li a:hover:not(.active) {
  background-color: #555;
  color: white;
}

</style>