<template>
  <div>
        <div class="frame">
          <div class="title">修改用户信息</div>
          <section>
            <div>用户名</div>
            <input placeholder="填写你的用户名">
          </section>

          <section>
            <div>专业</div>
            <input placeholder="填写你的专业">
          </section>

          <div class="avatar">
            <div class="text">修改头像：</div>
            <div class="upload">
              <div class="uploadButton">上传图片</div>
              <input type="file" @change="getImageFile($event)"/>
            </div>
            <img :src="previewImage"/>
          </div>
          <div class="button">
            <button class="ok" @click="updateUser">确定</button>
            <!--        <div class="cancel" @click="setInfoEditPage(false)">取消</div>-->
          </div>
        </div>
      </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ManagePersonInfo",
  data() {
    return {
      /**
       * 预上传的头像图片文件
       */
      preUploadImage: undefined,
      /**
       * 预览头像
       */
      previewImage: '',
      user: {
        avatar: ''
      }
    }
  },
  methods: {
    /**
     * 获取选择的文件并显示到预览图
     */
    getImageFile(e) {
      this.preUploadImage = e.target.files[0];
      let reader = new FileReader();
      reader.onload = () => {
        this.previewImage = reader.result;
      };
      reader.readAsDataURL(this.preUploadImage);
    },
    /**
     * 更新用户信息
     */
    async updateUser() {
      // let loading = showLoading('45vw', '6vh', '请求更新信息中...');
      console.log("请求更新中...");
      // 上传头像
      if (this.preUploadImage !== undefined) {
        let form = new FormData();
        form.append('image', this.preUploadImage);
        try {
          const response = await axios({
            method: 'PUT',
            url: '/api/avatar/upload',
            data: form,
          });
          if (!response.data.success) {
            alert("上传头像失败!" + response.data.message);
            // loading.destory();
            return;
          }
          this.user.avatar = response.data.data;
          // console.log(response.data.data);
        } catch (error) {
          // loading.destory();
          console.log("上传头像失败!" + error.response.data.message);
          return;
        }
      }
      // 提交用户信息
      // console.log(this.$store.state.token)
      try {
        const response = await axios({
          method: 'PATCH',
          url: '/api/user/updateUserInfo',
          data: this.user,
          headers: {
            token: this.$store.state.token
          }

        });
        if (!response.data.success) {
          console.log('修改用户信息失败' + response.data.message)
          // loading.destory();
          return;
        }
        // loading.destory();
        console.log('修改用户信息成功!');
        // 刷新本地储存的用户数据
        this.$store.commit('SET_AVATAR', this.user.avatar)
      } catch (error) {
        console.log('修改用户信息失败!');
        // loading.destory();
      }
    },


  }

}
</script>

<style scoped>
.frame {
  position: relative;
  z-index: 1;
  height: 71vh;
  left: 20vw;
  width: 50vw;
  background-color: #faf7f5;
}

.frame img {
  width: 60%;
  height: 255px;
  border-radius: 15px;
}

</style>