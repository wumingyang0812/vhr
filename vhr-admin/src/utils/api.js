import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'

axios.interceptors.response.use(success => {
    if (success.status && success.status === 200 && success.data.status === 500) {
        Message.error({ message: success.data.msg })
        return;
    }
    if (success.data.msg) {
        Message.success({ message: success.data.msg })
    }
    return success.data;
}, error => {
    if (error.response.status == 504 || error.response.status == 404) {
        Message.error({ message: '服务器被吃了( ╯□╰ )' })
    } else if (error.response.status == 403) {
        Message.error({ message: '权限不足，请联系管理员' })
    } else if (error.response.status == 401) {
        Message.error({ message: error.response.data.msg ? error.response.data.msg : '尚未登录，请登录' })
        window.sessionStorage.removeItem("user");
        router.push("/");
    } else {
        if (error.response.data.msg) {
            Message.error({ message: error.response.data.msg })
        } else {
            Message.error({ message: '未知错误!' })
        }
    }
    return;
})
// post请求
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: url,
        data: params
    })
}
// 删除请求
export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: url,
        params: params
    })
}

//修改请求
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: url,
        data: params
    })
}

// get请求
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: url,
        params: params
    })
}