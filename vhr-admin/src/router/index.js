import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import Welcome from '../components/Welcome.vue'
import EmpBasic from '../views/emp/EmpBasic.vue'
import SysHr from '../views/sys/SysHr.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect: '/welcome',
    children: [{ path: '/welcome', component: Welcome },
    { path: '/emp/basic', component: EmpBasic },
    { path: '/sys/hr', component: SysHr }]
  }
]

const router = new VueRouter({
  routes
})

export default router
