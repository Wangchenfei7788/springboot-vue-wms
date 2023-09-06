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
        name:'index',
        component:()=>import('../components/IndexHome.vue'),
        children:[
            {
                path:'/HomeHome',
                name:'home',
                component:()=>import('../components/HomeHome.vue'),
                meta:{
                    title:'首页'
                }
            },
           /*{
                path:'/AdminManger',
                name:'admin',
                component:()=>import('../components/admin/AdminManger.vue'),
                meta:{
                    title:'管理员管理'
                }
            },
            {
                path:'/UserManger',
                name:'user',
                component:()=>import('../components/user/UserManger.vue'),
                meta:{
                    title:'用户管理'
                }
            },
            {
                path:'/StorageManger',
                name:'storage',
                component:()=>import('../components/storage/StorageManger.vue'),
                meta:{
                    title:'仓库管理'
                }
            },
            {
                path:'/GoodTypeManger',
                name:'goodtype',
                component:()=>import('../components/goodtype/GoodTypeManger.vue'),
                meta:{
                    title:'产品分类管理'
                }
            },
            {
                path:'/GoodManger',
                name:'goodmanger',
                component:()=>import('../components/good/GoodManger.vue'),
                meta:{
                    title:'产品管理'
                }
            },
            {
                path:'/RecordManger',
                name:'record',
                component:()=>import('../components/record/RecordManger.vue'),
                meta:{
                    title:'记录管理'
                }
            },*/
        ]
    }
]
const router = new VueRouter({
    mode: 'history',
    routes
})

export function resetRouter() {
    router.matcher = new VueRouter({
        mode: 'history',
        routes: []
    }).matcher
}

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
export default router;