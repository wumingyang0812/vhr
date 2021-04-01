<template>
  <el-container class="home-container">
    <el-header>
      <div>
        <h1 class="home-title">人事管理系统</h1>
      </div>
      <el-button type="warning" size="mini" plain @click="logout"
        >退出</el-button
      >
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#409eff"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          router
          :default-active="activePath"
        >
          <!-- 一级菜单 -->
          <el-submenu
            :index="index + ''"
            v-for="(item, index) in routes"
            :key="index"
          >
            <template slot="title">
              <i :class="item.iconcls"></i>
              <span>{{ item.name }}</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item
              :index="child.path"
              v-for="(child, indexj) in item.children"
              :key="indexj"
              @click="saveNavState(child.path)"
            >
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{ child.name }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      // 表示导航菜单栏是否折叠
      isCollapse: false,
      // 被激活的链接地址
      activePath: ""
    };
  },
  computed: {
    routes() {
      return this.$store.state.routes;
    }
  },
  created() {
    this.activePath = window.sessionStorage.getItem("activePath");
  },
  methods: {
    logout() {
      this.postRequest("/logout").then(resp => {
        window.sessionStorage.removeItem("user");
        window.sessionStorage.removeItem("activePath");
        this.$router.push("/");
      });
    },
    // 点击按钮切换导航菜单的折叠和展开
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
    // 保存链接的激活状态
    saveNavState(activePath) {
      window.sessionStorage.setItem("activePath", activePath);
      this.activePath = activePath;
    }
  }
};
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: #409eff;
  color: #fff;
  display: flex;
  padding-left: 0;
  /* 左右贴边 */
  justify-content: space-between;
  align-items: center;
}
.home-title {
  width: 200px;
  text-align: center;
}
.el-aside {
  background-color: #545c64;
  .el-menu {
    border-right: none;
  }
}

.el-main {
  background-color: #fff;
}
.toggle-button {
  background-color: #545c64;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>
