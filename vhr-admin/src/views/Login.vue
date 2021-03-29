<template>
  <div class="login_container">
    <div
      v-loading="loading"
      element-loading-text="正在登录..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      class="login_box"
    >
      <h2 class="login_form_title">vhr人事管理系统</h2>
      <!-- 登录表单区 -->
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        class="login_form"
        label-width="0"
      >
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
            prefix-icon="el-icon-user-solid"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="loginForm.password"
            placeholder="请输入密码"
            prefix-icon="el-icon-key"
          >
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="btn" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      // 登录表单的数据绑定对象
      loginForm: {
        username: "admin",
        password: "123"
      },
      // 表单验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username: [
          { required: true, message: "请输入用户名称", trigger: "blur" },
          {
            min: 3,
            max: 11,
            message: "长度在 3 到 11 个字符",
            trigger: "blur"
          }
        ],
        // 验证密码是否合法
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 3,
            max: 11,
            message: "长度在 3 到 11 个字符",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    login() {
      this.$refs.loginFormRef.validate(valid => {
        if (!valid) return;
        this.loading = true;
        this.postRequest("/login", this.$qs.stringify(this.loginForm)).then(
          resp => {
            this.loading = false;
            if (resp) {
              this.$store.commit("INIT_CURRENTHR", resp.obj);
              window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
              this.$router.push("/home");
            }
          }
        );
      });
    }
  }
};
</script>

<style scoped>
.login_container {
  background-color: skyblue;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.login_form {
  position: absolute;
  bottom: 15px;
  width: 100%;
  padding: 0 50px;
  box-sizing: border-box;
}
.btn {
  width: 100%;
}
.login_form_title {
  font-size: 24px;
  color: #409eff;
  margin-top: 45px;
  text-align: center;
}
</style>
