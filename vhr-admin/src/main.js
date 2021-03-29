import Vue from 'vue'
import App from './App.vue'
import less from 'less'
import router from './router'
import './plugins/element.js'
// 导入全局样式表
import './assets/css/global.css'
// 导入qs
import qs from 'qs'
// 导入vuex
import store from './store'
import { postRequest, getRequest } from './utils/api'
// 导入初始化导航菜单工具
import { initMenu } from './utils/menus'

Vue.prototype.postRequest = postRequest
Vue.prototype.getRequest = getRequest
Vue.prototype.$qs = qs
Vue.config.productionTip = false
// 路由导航卫士
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next()
  } else {
    if (window.sessionStorage.getItem('user')) {
      initMenu(router, store)
      next();
    } else {
      next('/login')
    }
  }
})

new Vue({
  router,
  store,
  less,
  render: h => h(App)
}).$mount('#app')
