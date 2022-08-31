<template>
  <div>
    <div class="head">
      <el-container>
        <el-header>
          <el-menu
              router
              class="el-menu-demo"
              mode="horizontal"
              @select="handleSelect"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
          >
            <el-menu-item @click.native="clickHall">图书馆占座系统</el-menu-item>
            <el-menu-item index="/hall">状态</el-menu-item>
            <el-menu-item index="/appointment1">预约</el-menu-item>
            <el-menu-item index="/record">记录</el-menu-item>
            <el-menu-item index="/notice">公告</el-menu-item>
            <el-menu-item index="/help">帮助</el-menu-item>
          </el-menu>
          <el-dropdown>
            <i>
              <el-avatar icon="el-icon-user-solid" :src="$store.state.avatar"></el-avatar>
            </i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="clickLogin" v-if="$store.state.noLogin">登录</el-dropdown-item>
              <el-dropdown-item @click.native="clickRegister" v-if="$store.state.noLogin">注册</el-dropdown-item>
              <el-dropdown-item @click.native="clickPersonalHall" v-if="!$store.state.noLogin">个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="logout" v-if="!$store.state.noLogin">登出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>
      </el-container>
    </div>
    <div class="main_window">
      <router-view></router-view>
      <div class="cloud" data-type="white_4" style="top: 238px;" data-speed="1"></div>
      <div class="cloud" data-type="white_2" style="top: 252px;" data-speed="2"></div>
      <div class="cloud" data-type="white_1" style="top: 481px;" data-speed="3"></div>
      <div class="cloud" data-type="white_1" style="top: 285px;" data-speed="4"></div>
      <div class="cloud" data-type="white_5" style="top: 391px;" data-speed="5"></div>
      <div class="cloud" data-type="white_4" style="top: 410px;" data-speed="6"></div>
      <div class="cloud" data-type="white_6" style="top: 494px;" data-speed="7"></div>
      <div class="cloud" data-type="white_6" style="top: 197px;" data-speed="8"></div>
      <div class="cloud" data-type="white_1" style="top: 254px;" data-speed="9"></div>
      <div class="cloud" data-type="white_2" style="top: 478px;" data-speed="10"></div>
      <div class="cloud" data-type="white_5" style="top: 133px;" data-speed="11"></div>
      <div class="cloud" data-type="white_5" style="top: 126px;" data-speed="12"></div>
      <div class="cloud" data-type="white_5" style="top: 291px;" data-speed="13"></div>
      <div class="cloud" data-type="white_5" style="top: 515px;" data-speed="14"></div>
      <div class="cloud" data-type="white_6" style="top: 410px;" data-speed="15"></div>
      <div class="cloud" data-type="white_1" style="top: 165px;" data-speed="16"></div>
      <div class="cloud" data-type="white_2" style="top: 130px;" data-speed="17"></div>
      <div class="cloud" data-type="white_6" style="top: 274px;" data-speed="18"></div>
      <div class="cloud" data-type="white_6" style="top: 550px;" data-speed="19"></div>
      <div class="cloud" data-type="white_2" style="top: 140px;" data-speed="20"></div>
      <div class="cloud" data-type="white_6" style="top: 312px;" data-speed="21"></div>
      <div class="cloud" data-type="white_6" style="top: 529px;" data-speed="22"></div>
      <div class="cloud" data-type="white_2" style="top: 133px;" data-speed="23"></div>
      <div class="cloud" data-type="white_1" style="top: 530px;" data-speed="24"></div>
      <div class="cloud" data-type="white_5" style="top: 541px;" data-speed="25"></div>
      <div class="cloud" data-type="white_1" style="top: 313px;" data-speed="26"></div>
      <div class="cloud" data-type="white_1" style="top: 552px;" data-speed="27"></div>
      <div class="cloud" data-type="white_1" style="top: 548px;" data-speed="28"></div>
      <div class="cloud" data-type="white_2" style="top: 438px;" data-speed="29"></div>
      <div class="cloud" data-type="white_2" style="top: 516px;" data-speed="30"></div>
      <div class="cloud" data-type="white_5" style="top: 556px;" data-speed="31"></div>
      <div class="cloud" data-type="white_5" style="top: 225px;" data-speed="32"></div>
      <div class="cloud" data-type="white_3" style="top: 286px;" data-speed="33"></div>
      <div class="cloud" data-type="white_2" style="top: 416px;" data-speed="34"></div>
      <div class="cloud" data-type="white_2" style="top: 300px;" data-speed="35"></div>
      <div class="cloud" data-type="white_6" style="top: 105px;" data-speed="36"></div>
      <div class="cloud" data-type="white_2" style="top: 281px;" data-speed="37"></div>
      <div class="cloud" data-type="white_1" style="top: 291px;" data-speed="38"></div>
      <div class="cloud" data-type="white_4" style="top: 196px;" data-speed="39"></div>
      <div class="cloud" data-type="white_6" style="top: 442px;" data-speed="40"></div>
    </div>

  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "Index",
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '/hall'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      if (this.$store.state.noLogin) {
        this.$notify({
          title: '请先登录',
          message: "若无账号可前往'注册'申请"
        });
      }
      console.log(key, keyPath);
    },
    clickLogin() {
      this.$router.push({name: "login"})
    },
    clickRegister() {
      this.$router.push({name: "register"})
    },
    clickHall() {
      this.$router.push({name: "hall"})
    },
    clickPersonalHall() {
      this.$router.push({name: "personalCenter"})
    },

    async logout() {
      let resp = await axios({
        url: '/api/user/logout',
        method: 'post'
      });

      localStorage.clear();
      sessionStorage.clear();

      this.$store.commit("resetState");

      this.$router.push({name: "login"});
      console.log(resp)
    }

  }

}
</script>

<style scoped>
.head {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
}

.el-header {
  background-color: #545c64;
  color: #333;
  line-height: 60px;
}

.el-dropdown {
  z-index: 100;
  top: -8vh;
  left: 46vw;
  position: relative;
}

.el-menu {
  width: 500px;
}

body {
  background-color: #81cee8;
}

.main_window {
  overflow: hidden;
  display: block;
  padding: 0;
  margin: 0 auto;
  width: 100vw;
  height: 100vh;
  position: relative;
}

div {
  margin: 0px;
  padding: 0px;
}

.cloud {
  width: 200px;
  height: 50px;
  background-color: #fff;
  border-radius: 30px;
  position: absolute;
  top: 100px;
}

[data-type=white_1]:before {
  content: "";
  display: block;
  width: 50px;
  height: 50px;
  top: -20px;
  left: 20px;
  background-color: #fff;
  border-radius: 30px;
  position: absolute;
  box-shadow: 80px 0 0 10px #fff, 40px 0 0 20px #fff, 110px 0 0 -5px #fff;
}

[data-type=white_2]:before {
  content: "";
  display: block;
  width: 50px;
  height: 50px;
  top: -20px;
  left: 25px;
  background-color: #fff;
  border-radius: 30px;
  position: absolute;
  box-shadow: 40px -10px 0 20px #fff, 80px -10px 0 10px #fff, 115px -5px 0 -10px #fff;
}

[data-type=white_3]:before {
  content: "";
  display: block;
  width: 50px;
  height: 50px;
  top: -20px;
  left: 25px;
  background-color: #fff;
  border-radius: 30px;
  position: absolute;
  box-shadow: 40px -10px 0 15px #fff, 90px 0px 0 15px #fff;
}

[data-type=white_4]:before {
  content: "";
  display: block;
  width: 50px;
  height: 50px;
  top: -20px;
  left: 20px;
  background-color: #fff;
  border-radius: 30px;
  position: absolute;
  box-shadow: 80px 0 0 10px #fff, 40px 0 0 20px #fff, 110px 0 0 -5px #fff,
  80px 25px 0 10px #fff, 40px 25px 0 20px #fff, 0 40px 0 -5px #fff;
}

[data-type=white_5]:before {
  content: "";
  display: block;
  width: 50px;
  height: 50px;
  top: -20px;
  left: 25px;
  background-color: #fff;
  border-radius: 30px;
  position: absolute;
  box-shadow: 40px -10px 0 20px #fff, 80px -10px 0 10px #fff, 115px -5px 0 -10px #fff,
  20px 30px 0 20px #fff, 70px 30px 0 10px #fff, 105px 40px 0 -10px #fff;
}

[data-type=white_6]:before {
  content: "";
  display: block;
  width: 50px;
  height: 50px;
  top: -20px;
  left: 25px;
  background-color: #fff;
  border-radius: 30px;
  position: absolute;
  box-shadow: 40px -10px 0 15px #fff, 90px 0 0 15px #fff,
  20px 30px 0 15px #fff, 80px 40px 0 15px #fff;
}

@-webkit-keyframes linemove {
  from {
    left: -200px;
  }
  to {
    left: 1456px;
  }
}

@-moz-keyframes linemove {
  from {
    left: -200px;
  }
  to {
    left: 1456px;
  }
}

@-ms-keyframes linemove {
  from {
    left: -200px;
  }
  to {
    left: 1456px;
  }
}

@-o-keyframes linemove {
  from {
    left: -200px;
  }
  to {
    left: 1456px;
  }
}

@keyframes linemove {
  from {
    left: -200px;
  }
  to {
    left: 1456px;
  }
}


[data-speed="1"] {
  animation: linemove 44.01936s;
  -webkit-animation: linemove 44.01936s;

}

[data-speed="2"] {
  animation: linemove 28.67113s;
  -webkit-animation: linemove 28.67113s;

}

[data-speed="3"] {
  animation: linemove 36.99853s;
  -webkit-animation: linemove 36.99853s;

}

[data-speed="4"] {
  animation: linemove 22.6682s;
  -webkit-animation: linemove 22.6682s;

}

[data-speed="5"] {
  animation: linemove 21.20363s;
  -webkit-animation: linemove 21.20363s;

}

[data-speed="6"] {
  animation: linemove 36.46404s;
  -webkit-animation: linemove 36.46404s;

}

[data-speed="7"] {
  animation: linemove 45.58272s;
  -webkit-animation: linemove 45.58272s;

}

[data-speed="8"] {
  animation: linemove 31.02697s;
  -webkit-animation: linemove 31.02697s;

}

[data-speed="9"] {
  animation: linemove 23.79332s;
  -webkit-animation: linemove 23.79332s;

}

[data-speed="10"] {
  animation: linemove 35.69691s;
  -webkit-animation: linemove 35.69691s;

}

[data-speed="11"] {
  animation: linemove 36.87823s;
  -webkit-animation: linemove 36.87823s;

}

[data-speed="12"] {
  animation: linemove 20.158s;
  -webkit-animation: linemove 20.158s;

}

[data-speed="13"] {
  animation: linemove 17.34752s;
  -webkit-animation: linemove 17.34752s;

}

[data-speed="14"] {
  animation: linemove 22.19251s;
  -webkit-animation: linemove 22.19251s;

}

[data-speed="15"] {
  animation: linemove 25.91683s;
  -webkit-animation: linemove 25.91683s;

}

[data-speed="16"] {
  animation: linemove 17.46605s;
  -webkit-animation: linemove 17.46605s;

}

[data-speed="17"] {
  animation: linemove 22.15188s;
  -webkit-animation: linemove 22.15188s;

}

[data-speed="18"] {
  animation: linemove 27.5939s;
  -webkit-animation: linemove 27.5939s;

}

[data-speed="19"] {
  animation: linemove 40.26929s;
  -webkit-animation: linemove 40.26929s;

}

[data-speed="20"] {
  animation: linemove 12.73873s;
  -webkit-animation: linemove 12.73873s;

}

[data-speed="21"] {
  animation: linemove 11.34359s;
  -webkit-animation: linemove 11.34359s;

}

[data-speed="22"] {
  animation: linemove 24.98947s;
  -webkit-animation: linemove 24.98947s;

}

[data-speed="23"] {
  animation: linemove 43.17049s;
  -webkit-animation: linemove 43.17049s;

}

[data-speed="24"] {
  animation: linemove 28.77146s;
  -webkit-animation: linemove 28.77146s;

}

[data-speed="25"] {
  animation: linemove 19.03643s;
  -webkit-animation: linemove 19.03643s;

}

[data-speed="26"] {
  animation: linemove 44.53789s;
  -webkit-animation: linemove 44.53789s;

}

[data-speed="27"] {
  animation: linemove 26.64436s;
  -webkit-animation: linemove 26.64436s;

}

[data-speed="28"] {
  animation: linemove 41.99245s;
  -webkit-animation: linemove 41.99245s;

}

[data-speed="29"] {
  animation: linemove 18.44574s;
  -webkit-animation: linemove 18.44574s;

}

[data-speed="30"] {
  animation: linemove 19.09319s;
  -webkit-animation: linemove 19.09319s;

}

[data-speed="31"] {
  animation: linemove 34.62887s;
  -webkit-animation: linemove 34.62887s;

}

[data-speed="32"] {
  animation: linemove 33.08773s;
  -webkit-animation: linemove 33.08773s;

}

[data-speed="33"] {
  animation: linemove 10.0134s;
  -webkit-animation: linemove 10.0134s;

}

[data-speed="34"] {
  animation: linemove 39.82966s;
  -webkit-animation: linemove 39.82966s;

}

[data-speed="35"] {
  animation: linemove 36.10109s;
  -webkit-animation: linemove 36.10109s;

}

[data-speed="36"] {
  animation: linemove 47.62616s;
  -webkit-animation: linemove 47.62616s;

}

[data-speed="37"] {
  animation: linemove 39.53287s;
  -webkit-animation: linemove 39.53287s;

}

[data-speed="38"] {
  animation: linemove 21.86695s;
  -webkit-animation: linemove 21.86695s;

}

[data-speed="39"] {
  animation: linemove 48.20212s;
  -webkit-animation: linemove 48.20212s;

}

[data-speed="40"] {
  animation: linemove 26.6844s;
  -webkit-animation: linemove 26.6844s;
}

.cloud {
  -webkit-animation-timing-function: linear;
  -webkit-animation-iteration-count: infinite;
  animation-timing-function: linear;
  animation-iteration-count: infinite;
  -webkit-animation-delay: -50s;
}
</style>