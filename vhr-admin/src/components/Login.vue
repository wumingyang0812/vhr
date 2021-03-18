<template>
  <div class="login_container">
    <div class="login_box">
      <h2 class="login_form_title">vhr人事管理系统</h2>
      <!-- 登录表单区 -->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" class="login_form" label-width="0">
        <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" prefix-icon="el-icon-key"></el-input>
        </el-form-item>
        <el-form-item class="btns">
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 登录表单的数据绑定对象
      loginForm: {
        username: '',
        password: ''
      },
      // 表单验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 3, max: 11, message: '长度在 3 到 11 个字符', trigger: 'blur' }
        ],
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 11, message: '长度在 6 到 11 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 点击重置按钮，重置登录表单
    resetLoginForm () {
      this.$refs.loginFormRef.resetFields()
    },
    login () {
      this.$refs.loginFormRef.validate(valid => {
        if (valid) {
          // this.$http.post('login', this.loginForm)
          this.$message.success('200，OK')
          this.$router.push('/home')
        } else {
          this.$message.error('500，error')
        }
      })
    }
  }
}

</script>

<style scoped>
  .login_container{
    background-color: skyblue;
    height: 100%;
  }
  .login_box{
    width: 450px;
    height: 300px;
    background-color: #fff;
    border-radius: 10px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);

  }
  .login_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 50px;
    box-sizing: border-box;
  }
  .btns{
    display: flex;
    justify-content: center;
  }
  .login_form_title{
    color: #409eff;
    margin-top: 45px;
    text-align: center;
  }
</style>
