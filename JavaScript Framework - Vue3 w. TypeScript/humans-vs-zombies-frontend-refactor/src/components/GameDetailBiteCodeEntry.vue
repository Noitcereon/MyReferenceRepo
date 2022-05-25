<script lang="ts">
import { mapGetters, mapState } from "vuex";
export default {
  computed: {
    ...mapState("biteCodeModule", ["biteCode"]),
    ...mapState("loginModule", ["user"]),
  },
};
</script>
<script setup lang="ts">
import { ref, computed, inject } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";

const store = useStore();
const route = useRoute();
const currentDate = new Date();
const keycloak: any = inject("kc");

let inputBiteCode = ref("");
let inputStory = ref("");

const addKillInGame = () => {
  const killData = {
    gameId: Number.parseInt(route.params.gameId[0]),
    theKiller: computed(() => store.state.loginModule.user),
    timestamp: currentDate.getTime(),
    biteCode: inputBiteCode.value.toUpperCase(),
    story: inputStory.value,
    keycloakToken: keycloak.token, //computed(() => store.state.loginModule.keycloakToken),
  };
  store.dispatch("biteCodeModule/addKillInGame", killData);
};
</script>

<template>
  <div class="text-center">
    <h1 class="text-2xl font-bold">Insert Bite Code:</h1>
    <input
      class="border-2 border-indigo-600 rounded"
      type="text"
      style="border: 2px"
      v-model="inputBiteCode"
    />
    <br />
    <h1 class="text-2xl font-bold">Story:</h1>
    <textarea class="border-2 border-indigo-600 rounded" v-model="inputStory" />
    <br />
    <button
      class="bg-blue-400 text-gray-800 hover:bg-blue-700 px-5 py-1 rounded"
      @click="addKillInGame()"
    >
      Submit Kill
    </button>
  </div>
</template>

<style scoped>
/* styling only present in here*/
</style>
