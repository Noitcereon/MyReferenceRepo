import { createWebHistory, createRouter, Router } from "vue-router";
import store from "./store/vuex-store";
import Home from "./components/Home.vue";
import GameDetails from "./components/GameDetail.vue";
import Admin from "./components/Admin.vue";
import AdminHome from "./components/AdminHome.vue";
import GameDetailTitleFragment from "./components/GameDetailTitleFragment.vue";
import GameDetailRegister from "./components/GameDetailRegister.vue";
import GameDetailMap from "./components/GameDetailMap.vue";
import GameDetailBiteCode from "./components/GameDetailBiteCode.vue";
import GameDetailBiteCodeEntry from "./components/GameDetailBiteCodeEntry.vue";
import GameDetailChat from "./components/GameDetailChat.vue";
import GameDetailSquadDetails from "./components/GameDetailSquadDetails.vue";
import GameDetailSquadRegister from "./components/GameDetailSquadRegistration.vue";
import IconAttribution from "./components/IconAttribution.vue";
import AdminCreateGame from "./components/AdminCreateGame.vue";
import AdminManageGame from "./components/AdminManageGame.vue";

const vuexStore: any = store;

// to = route navigated to, from = route navigated away from
function redirectToHomeIfNotLoggedIn(to: any, from: any) {
  if (vuexStore.state.loginModule.loggedIn === false) {
    // redirect to the route with the name specified.
    return { name: "home" };
  }
}
function redirectToHomeIfNotAdmin(to: any, from: any) {
  const isAdmin = vuexStore.getters["loginModule/userIsAdmin"];
  console.log("isAdmin", isAdmin);
  if (isAdmin === false || isAdmin === undefined) {
    alert("Requires admin privileges");
    return { name: "home" };
  }
}

const routes = [
  {
    name: "home",
    path: "/",
    component: Home,
  },
  {
    path: "/game/:gameId",
    // route guard. to = route navigated to, from: route navigated away from
    beforeEnter: [redirectToHomeIfNotLoggedIn],
    children: [
      {
        name: "gameTitle",
        path: "", // gets loaded by default
        component: GameDetailTitleFragment,
      },
      {
        name: "gameRegister",
        path: "register",
        component: GameDetailRegister,
      },
      {
        name: "gameMap",
        path: "map",
        component: GameDetailMap,
      },
      {
        name: "biteCode",
        path: "bite-code",
        component: GameDetailBiteCode,
      },
      {
        name: "biteCodeEntry",
        path: "bite-code-entry",
        component: GameDetailBiteCodeEntry,
      },
      {
        name: "gameChat",
        path: "chat",
        component: GameDetailChat,
      },
      {
        name: "gameSquadDetails",
        path: "squad-details",
        component: GameDetailSquadDetails,
      },
      {
        name: "gameSquadRegister",
        path: "squad-register",
        component: GameDetailSquadRegister,
      },
    ],
    components: {
      default: GameDetails,
    },
  },
  {
    path: "/admin",
    beforeEnter: [redirectToHomeIfNotLoggedIn, redirectToHomeIfNotAdmin],
    component: Admin,
    children: [
      {
        name: "admin-home",
        path: "",
        component: AdminHome
      },
      {
        name: "create-game",
        path: "create-game",
        component: AdminCreateGame,
      },
      {
        name: "manage-game",
        path: "manage-game/:gameId",
        component: AdminManageGame,
      },
    ],
  },
  {
    name: "game-attribution",
    path: "/icon-attribution",
    component: IconAttribution,
  },
];

const router: Router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
