import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import MapView from "../views/MapView.vue";
import ListView from "../views/ListView.vue";
import PokedexView from "../views/PokedexView.vue";
import RegisterView from "../views/RegisterView.vue";
import LoginView from "../views/LoginView.vue";
import AboutView from "../views/AboutView.vue";
import UserInfoView from "../views/UserInfoView.vue";
import { useAttrs } from "vue";
import { useAuthStore } from "../stores/auth";
import AddSightingView from "../views/AddSightingView.vue";



const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      redirect: {
        name: 'map'
      }
    },
    {
      path: "/map",
      name: "map",
      component: MapView,
      meta: {
        requires: "auth",
      }
    },
    {
      path: "/list",
      name: "list",
      component: ListView,
      meta: {
        requires: "auth",
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/addpokemon",
      name: "addpokemon",
      component: AddSightingView
    },
    {
      path: "/userinfo",
      name: "userinfo",
      component: UserInfoView,
      meta: {
        requires: "auth",
      }
    },
    {
      path: "/pokedex",
      name: "pokedex",
      component: PokedexView
    }
  ],
});

router.beforeEach((to, from) => {
  const authStore = useAuthStore();
  if (to.meta.requires === "auth") {
    if (authStore.getToken) {
      //maybe check if token is still valid
    } else {
      //if no token redirect to login view
      return {
        path: "/login",
        replace: true
      }

    }
  }

});

export default router;
