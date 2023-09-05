import vue from 'vue'
import Vuex from 'vuex'
import router, {resetRouter} from "../router";
import createPersistedState from 'vuex-persistedstate';


vue.use(Vuex)

function addNewRoute(menuList){
    console.log(menuList)

    let routes = router.options.routes
    console.log(routes)
    routes.forEach(routeItem =>{
        if (routeItem.path == "/IndexHome") {


            menuList.forEach(menu => {
                let childRoute = {
                    path: '/' + menu.menuClick,
                    name: menu.menuName,
                    meta: {
                        title: menu.menuName
                    },
                    component:(resolve)=>require([`../components/${menu.menuComponent}`],resolve),

                }
                routeItem.children.push(childRoute)
            })
        }
    })
    router.addRoutes(routes)
}

export default new Vuex.Store({
    state: {
        menu:[]

    },
    mutations: {
     setMenu(state,menuList){
         state.menu = menuList

         addNewRoute(menuList)
     }
    },
    getters:{
        getMenu(state){
            return state.menu
        }
    },
    plugins: [createPersistedState()]
})
