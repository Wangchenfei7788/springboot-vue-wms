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
        component:()=>import('../components/IndexHome.vue'),
        children:[
            {
                path:'/HomeHome',
                name:'home',
                component:()=>import('../components/HomeHome.vue'),
                meta:{
                    title:'首页'
                }
            }
        ]
    }
]
    const router =new VueRouter({
        mode:'history',
        routes
    })
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
export  default router;