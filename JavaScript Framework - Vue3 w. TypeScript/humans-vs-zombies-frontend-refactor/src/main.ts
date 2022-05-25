import { createApp } from "vue";
import './index.css';

import App from "./App.vue";
import router from "./router";
import store from "./store/vuex-store";
import Keycloak from "keycloak-js";

const keycloak = new (Keycloak as any)({
    url: "https://dk-hvz-keycloak.herokuapp.com/auth",
    realm: "hvz",
    clientId: "hvz-api" 
});

async function initKeycloak() {
    const authenticated = await keycloak.init({
        flow: 'implicit',
        pkceMethod: 'S256'
    });
}

initKeycloak();

const app = createApp(App);

app.provide("kc", keycloak);
app.use(router);
app.use(store);
app.mount("#app");