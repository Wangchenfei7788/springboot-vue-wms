//路由文件
import VueRouter from "vue-router";
const routes = [
    {
        path:'/',
        name:'login',
        component:()=>import('../components/LoginHome.vue')
    },
    {
        path:'/IndexHome',
        name:'login',
        component:()=>import('../components/IndexHome.vue')
    }
]
    const router =new VueRouter({
        mode:'history',
        routes
    })
export  default router;