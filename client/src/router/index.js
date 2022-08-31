import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Index from '../views/Index.vue'
import Hall from "../views/Hall";
import Register from "../views/Register";
import Appointment from "../views/Appointment";
import Appointment1 from "../views/Appointment1";
import Record from "../views/Record";
import Notice from "../views/Notice";
import Help from "../views/Help";
import Appointment2 from "../views/Appointment2";
import Appointment3 from "../views/Appointment3";
import AdminLogin from "../views/AdminLogin";
import Admin from "../views/Admin";
import axios from "axios";
import store from "../store/index"
import PersonalCenter from "@/views/PersonalCenter";
import ManagePersonInfo from "@/views/ManagePersonInfo";
import WebSocket from "@/views/WebSocket";


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        component: Index,
        redirect: '/hall',
        children: [
            {
                path: '/login',
                name: 'login',
                component: Login
            },
            {
                path: '/register',
                name: 'register',
                component: Register
            },
            {
                path: '/hall',
                name: 'hall',
                component: Hall
            },
            {
                path: "/appointment",
                name: "appointment",
                component: Appointment,
                redirect: '/appointment1',
                children: [
                    {
                        path: "/appointment1",
                        name: "appointment1",
                        component: Appointment1
                    },
                    {
                        path: "/appointment2",
                        name: "appointment2",
                        component: Appointment2
                    },
                    {
                        path: "/appointment3",
                        name: "appointment3",
                        component: Appointment3
                    }
                ]
            },
            {
                path: "/record",
                name: "record",
                component: Record
            },
            {
                path: "/notice",
                name: "notice",
                component: Notice
            },
            {
                path: "/help",
                name: "help",
                component: Help
            },
            {
                path: "/adminLogin",
                name: "adminLogin",
                component: AdminLogin
            },
            {
                path: "/personalCenter",
                name: "personalCenter",
                component: PersonalCenter,
                redirect: "/managePersonInfo",
                children: [{
                    path: "/managePersonInfo",
                    name: "managePersonInfo",
                    component: ManagePersonInfo
                }]
            },
            {
                path: "/websocket",
                name: "websocket",
                component: WebSocket
            }
        ]

    },
    {
        path: "/admin",
        component: Admin

    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((async (to, from, next) => {
        if (to.path.startsWith('/login') || to.path.startsWith('/adminLogin') || to.path.startsWith('/register')) {//第一次前往登录页，后面我们把它给隐藏
            window.localStorage.clear()//每前往一次登录页就将信息去除
            next()
        } else {//如果不是前往登录页
            try {//判断是否登录
                const response = await axios.get('/api/user/isLogin');
                //console.log(response);
                if (!response.data.success) {//失败则跳转到登录页
                    next({path: '/login'})
                    // this.router.push({name:'login'})
                }
                //获取角色信息
                store.state.role = response.data.data.roles[0].roleName;
                //拦截管理员登录的信息
                if (to.path.startsWith('/admin')) {//如果前往的是管理员界面
                    if (store.state.role !== 'ROLE_ADMIN') {
                        console.log('权限不够');
                        alert('权限不够!');
                        //跳转回管理员登录界面
                        //next({path: '/adminLogin'})
                        this.router.push({name:'adminLogin'})
                    }
                }
                //防止token信息由于页面刷新消失
                let token = window.localStorage.getItem('token')
                store.commit('SET_TOKEN', token)

                // console.log(store.state.role);
                store.state.avatar = response.data.data.avatar;//将头像信息保存到vuex中
                // console.log(response)
                store.state.id = response.data.data.id;//将id信息存放到vuex中
                store.state.noLogin=false;
                // console.log(store.state.id)
                // console.log(store.state.avatar);

                if (!!!store.state.username) {//如果在store中还没有设置用户名
                    store.state.username = response.data.data.userName;
                }
            } catch (e) {//出错则跳转到登录页
                console.log(e);
                // next({path: '/login'})
                this.router.push({name:'login'})
            }
        }
        next()
    }
))

export default router
