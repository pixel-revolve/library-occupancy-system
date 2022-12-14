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
        if (to.path.startsWith('/login') || to.path.startsWith('/adminLogin') || to.path.startsWith('/register')) {//??????????????????????????????????????????????????????
            window.localStorage.clear()//??????????????????????????????????????????
            next()
        } else {//???????????????????????????
            try {//??????????????????
                const response = await axios.get('/api/user/isLogin');
                //console.log(response);
                if (!response.data.success) {//???????????????????????????
                    next({path: '/login'})
                    // this.router.push({name:'login'})
                }
                //??????????????????
                store.state.role = response.data.data.roles[0].roleName;
                //??????????????????????????????
                if (to.path.startsWith('/admin')) {//?????????????????????????????????
                    if (store.state.role !== 'ROLE_ADMIN') {
                        console.log('????????????');
                        alert('????????????!');
                        //??????????????????????????????
                        //next({path: '/adminLogin'})
                        this.router.push({name:'adminLogin'})
                    }
                }
                //??????token??????????????????????????????
                let token = window.localStorage.getItem('token')
                store.commit('SET_TOKEN', token)

                // console.log(store.state.role);
                store.state.avatar = response.data.data.avatar;//????????????????????????vuex???
                // console.log(response)
                store.state.id = response.data.data.id;//???id???????????????vuex???
                store.state.noLogin=false;
                // console.log(store.state.id)
                // console.log(store.state.avatar);

                if (!!!store.state.username) {//?????????store???????????????????????????
                    store.state.username = response.data.data.userName;
                }
            } catch (e) {//???????????????????????????
                console.log(e);
                // next({path: '/login'})
                this.router.push({name:'login'})
            }
        }
        next()
    }
))

export default router
