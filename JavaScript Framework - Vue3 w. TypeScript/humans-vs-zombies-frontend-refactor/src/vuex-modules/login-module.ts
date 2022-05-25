import { KeycloakInstance } from "keycloak-js";
import { ICommonResponse } from "../models/ICommonResponse";
import { IGame } from "../models/IGame";
import { IUser } from "../models/IUser";

const loginModule = {
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
      console.log("userInfo", payload);
      state.user = payload;
    },
    setUserPrivileges(state: any, keycloak: KeycloakInstance) {
      const tokenParsed: any = keycloak.tokenParsed;
      console.log("userPrivileges", tokenParsed.roles);
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

      const urlString = "https://dk-hvz-backend.herokuapp.com/api/login";

      const response = await fetch(urlString, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + keycloak.token,
        },
      });
      const user: ICommonResponse<IUser> = await response.json();

      context.commit("setUser", user.payload);

      // extracted to seperate variable for TS reasons.
      // const tokenParsed: any = keycloak.tokenParsed;

      // make a user based on parsed token data (TODO: fetch from our API instead)
      // const user: IUser = {
      //   user_id: -1, // temporary solution, while fetch user from our api is not possible.
      //   first_name: tokenParsed.given_name,
      //   last_name: tokenParsed.family_name,
      // };
    },
    logout(context: any, keycloak: KeycloakInstance) {
      context.commit("setUser", null);
      context.commit("setLoginStatus", keycloak.authenticated);
      context.commit("setUserPrivileges", []);
    },
  },
};

export default loginModule;
