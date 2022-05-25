<template>
  <div v-if="!loggedIn">
    <button
      @click="keycloak.login()"
      class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
    >
      Login
    </button>
  </div>
  <div v-if="loggedIn">
    <span class="mr-2">{{user.first_name}} {{user.last_name}}</span>
    <button
      @click="logout()"
      class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
    >
      Logout
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref, /*watch,*/ inject, onMounted, Ref, computed } from "vue";
import { useStore } from "vuex";

const store = useStore();
const user = computed(() => store.state.loginModule.user);
const loggedIn = computed(() => store.state.loginModule.loggedIn);

// retrieve keycloak instance from main.ts
const keycloak: Ref<any> = ref(inject("kc"));

onMounted(() => {
  keycloak.value.onAuthSuccess = () => {
    console.log("Keycloak authenticated");

    // call loginSuccess action in the loginModule, which in turn logs the user in
    // and retrieves and stores the active user to state.
    store.dispatch("loginModule/loginSuccess", keycloak.value);
  };
});

function logout() {
  keycloak.value.logout();
  store.dispatch("loginModule/logout", keycloak.value);
}

// #region reference code
// // watch works directly on a ref (triggers on state change for keycloak variable)
// watch(keycloak, async (newLoginState) => {
//   console.log("Im in the watcher");
//   console.log(newLoginState);
// });
// #endregion
</script>
