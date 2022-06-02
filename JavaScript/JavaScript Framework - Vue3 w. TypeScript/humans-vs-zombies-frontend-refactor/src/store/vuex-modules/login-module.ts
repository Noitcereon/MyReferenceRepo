import { KeycloakInstance } from "keycloak-js";
import { Module } from "vuex";
import { ICommonResponse } from "../../models/ICommonResponse";
import { IGame } from "../../models/IGame";
import { IUser } from "../../models/IUser";
import baseApiUrl from "../../api";

const loginModule : Module<any, any>= {
  namespaced: true,
  state() {
    const loggedIn = false;
    const user = {};
    const keycloak = {};
    const keycloakToken: String | undefined = "";
    const userPrivileges: String[] | undefined = [];
    return {
      loggedIn,
      user,
      keycloak,
      keycloakToken,
      userPrivileges,
    };
  },
  getters: {
    getUser(state: any) {
      return state.user;
    },
    userIsAdmin(state: any) {
      const userRoles: String[] = state.userPrivileges;
      const isAdmin: Boolean = userRoles.some(
        (role) => role === "Administrator"
      );
      return isAdmin;
    },
    // getKeycloakToken(state: any) {
    //   return state.keycloakToken;
    // }
  },
  mutations: {
    setLoginStatus(state: any, authenticated: Boolean) {
      console.log("loggedIn", authenticated);
      state.loggedIn = authenticated;
    },
    setUser(state: any, payload: any) {
      // console.log("userInfo", payload);
      state.user = payload;
    },
    setUserPrivileges(state: any, keycloak: KeycloakInstance) {
      const tokenParsed: any = keycloak.tokenParsed;
      // console.log("userPrivileges", tokenParsed.roles);
      state.userPrivileges = tokenParsed.roles;
    },
    // setKeycloakToken(state: any, token: String) {
    //   state.keycloakToken = token;
    // }
  },
  actions: {
    async loginSuccess(context: any, keycloak: KeycloakInstance) {
      context.commit("setLoginStatus", keycloak.authenticated);
      context.commit("setUserPrivileges", keycloak);
      // context.commit("setKeycloakToken", keycloak.token);
  
      const response = await fetch(baseApiUrl + "login", {
        method: "GET",
        headers: {
          Authorization: "Bearer " + keycloak.token,
        },
      });
      const user: ICommonResponse<IUser> = await response.json();

      context.commit("setUser", user.payload);
    },
    logout(context: any, keycloak: KeycloakInstance) {
      context.commit("setUser", null);
      context.commit("setLoginStatus", keycloak.authenticated);
      context.commit("setUserPrivileges", []);
    },
  },
};

export default loginModule;
