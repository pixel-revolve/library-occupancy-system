<template>
  <div class="chat-box">
    <header>聊天室 (在线:{{ count }}人)</header>
    <div class="msg-box" ref="msg-box">
      <div
          v-for="(i,index) in list"
          :key="index"
          class="msg"
          :style="i.userId == userId?'flex-direction:row-reverse':''"
      >
        <div class="user-head">
          <img :src="i.avatar" height="30" width="30" :title="i.username">
        </div>
        <div class="user-msg">
          <span :style="i.userId == userId?' float: right;':''"
                :class="i.userId == userId?'right':'left'">{{ i.content }}</span>
        </div>
      </div>
    </div>
    <div class="input-box">
      <input type="text" ref="sendMsg" v-model="contentText" @keyup.enter="sendText()"/>
      <div class="btn" :class="{['btn-active']:contentText}" @click="sendText()">发送</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WebSocket.vue",
  data() {
    return {
      ws: null,
      count: 0,
      userId: this.$store.getters.id, // 当前用户ID
      username: this.$store.getters.name, // 当前用户昵称
      avatar: this.$store.getters.avatar, // 当前用户头像
      list: [], // 聊天记录的数组
      contentText: "" // input输入的值
    };
  },
  mounted() {
    this.initWebSocket();
  },
  destroyed() {
    // 离开页面时关闭websocket连接
    this.ws.onclose(undefined);
  },
  methods: {
    // 发送聊天信息
    sendText() {
      let _this = this;
      _this.$refs["sendMsg"].focus();
      if (!_this.contentText) {
        return;
      }
      let params = {
        userId: _this.userId,
        username: _this.username,
        avatar: _this.avatar,
        msg: _this.contentText,
        count: _this.count
      };
      _this.ws.send(JSON.stringify(params)); //调用WebSocket send()发送信息的方法
      _this.contentText = "";
      setTimeout(() => {
        _this.scrollBottm();
      }, 500);
    },
    // 进入页面创建websocket连接
    initWebSocket() {
      let _this = this;
      // 判断页面有没有存在websocket连接
      if (window.WebSocket) {
        var serverHot = window.location.hostname;
        let sip = '房间号'
        // 填写本地IP地址 此处的 :9101端口号 要与后端配置的一致！
        var url = 'ws://' + serverHot + ':9101' + '/groupChat/' + sip + '/' + this.userId; // `ws://127.0.0.1/9101/groupChat/10086/聊天室`
        let ws = new WebSocket(url);
        _this.ws = ws;
        ws.onopen = function (e) {
          console.log("服务器连接成功: " + url);
        };
        ws.onclose = function (e) {
          console.log("服务器连接关闭: " + url);
        };
        ws.onerror = function () {
          console.log("服务器连接出错: " + url);
        };
        ws.onmessage = function (e) {
          //接收服务器返回的数据
          let resData = JSON.parse(e.data)
          _this.count = resData.count;
          _this.list = [
            ..._this.list,
            {userId: resData.userId, username: resData.username, avatar: resData.avatar, content: resData.msg}
          ];
        };
      }
    },
    // 滚动条到底部
    scrollBottm() {
      let el = this.$refs["msg-box"];
      el.scrollTop = el.scrollHeight;
    }
  }
};
</script>

<style scoped>

</style>