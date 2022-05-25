<script lang="ts">
import { mapGetters, mapState } from "vuex";
export default {
  computed: {
    ...mapState("loginModule", ["user"]),
  },
};
</script>
<script setup lang="ts">
import { onMounted, ref, inject, computed } from "vue";
import { useRoute } from "vue-router";
import { useStore } from "vuex";
import zombieImage from "../assets/zombie.png";
import manImage from "../assets/man.png";
import livingDeadImage from "../assets/living-dead.png";
import patientImage from "../assets/patient.png";

const route = useRoute();
const store = useStore();
const keycloak: any = inject("kc");
let inputPlayerType = ref("");
let inputPlayerZero = ref("");
let currentGameID = ref(0);

onMounted(() => {
  currentGameID.value = Number.parseInt(route.params.gameId[0]);
});

const addPlayerToGame = async () => {
  if (inputPlayerType.value == "" || inputPlayerZero.value == "") {
    alert("insert yes and no value for player info");
    return;
  }

  console.log("Success, you are allowed to add new players to the game");

  let newPlayer = {
    currentUser: computed(() => store.state.loginModule.user),
    gameID: currentGameID.value,
    playerType: inputPlayerType.value,
    playerZero: inputPlayerZero.value,
    token: keycloak.token,
  };

  await store.dispatch("gameRegistrationModule/addPlayerInGame", newPlayer);
  await store.dispatch("loginModule/loginSuccess", keycloak);
};
</script>

<template>
<article class="bg-white bg-opacity-60 p-3">
  <label> is the new player human?: </label> <br />
  <input type="radio" v-model="inputPlayerType" value="y" />yes
  <input type="radio" v-model="inputPlayerType" value="n" />no
  <div v-if="inputPlayerType === 'y'">
    <img :src="manImage" class="h-12" />
  </div>
  <div v-else-if="inputPlayerType === 'n'">
    <img :src="zombieImage" class="h-12" />
  </div>
  <br />
  <label> is the new player patient zero?: </label> <br />
  <input type="radio" v-model="inputPlayerZero" value="y" />yes
  <input type="radio" v-model="inputPlayerZero" value="n" />no
  <div v-if="inputPlayerZero === 'y'">
    <img :src="livingDeadImage" class="h-12" />
  </div>
  <div v-else-if="inputPlayerZero === 'n'">
    <img :src="patientImage" class="h-12" />
  </div>
  <br />
  <button
    class="bg-blue-400 text-gray-800 hover:bg-blue-700 px-5 py-1 rounded"
    @click="addPlayerToGame()"
  >
    Register Player
  </button>

  <router-view></router-view>
  </article>
</template>

<style scoped></style>
