<script setup lang="ts">
// script things

import { computed, inject, onMounted, ref, Ref } from "vue";
import { useRoute } from "vue-router";
import { useStore } from "vuex";
import baseApiUrl from "../api";
import { ICommonResponse } from "../models/ICommonResponse";
import { IPlayer } from "../models/IPlayer";
import PlayerList from "./PlayerList.vue";

const store = useStore();
const route = useRoute();

const loggedIn = computed(() => store.state.loginModule.loggedIn);
const game = computed(() => store.state.gameListModule.game);
const keycloak: any = inject("kc");
const payload = { token: keycloak.token, gameId: route.params.gameId };

const players: Ref<IPlayer[]> = ref([]);
const retrievePlayers = async () => {
  const playersResponse = await fetch(
    `${baseApiUrl}game/${payload.gameId}/player`,
    {
      headers: {
        Authorization: `Bearer ${payload.token}`,
        "Content-Type": "application/json",
      },
    }
  );
  const commonResponse: ICommonResponse<IPlayer[]> =
    await playersResponse.json();
  players.value = commonResponse.payload;
};

onMounted(() => {
  // fetch game information
  store.dispatch("gameListModule/fetchGameById", payload);

  retrievePlayers();

  // TODO: fetch missions

  keycloak.onAuthSuccess = function () {
    if (loggedIn.value == false) {
      store.dispatch("loginModule/loginSuccess", keycloak);
    }
  };
});

function changeGameInfo(gameId: number) {
  // TODO: update game info with inputs (game name, description)
  alert("Sorry - Not implemented - We are working on it");
}
async function changeState(player: IPlayer) {
  const isHuman: boolean = player.is_human === "y" ? true : false;
  try {
    const headers = new Headers();
    headers.set("method", "PATCH");
    headers.set("authorization", `Bearer ${payload.token}`);
    
    // TODO: switch state from zombie to human or human to zombie (the below fetch request is a placeholder made before the APi implemened the feature of updating a player)
    const response = await fetch(`${baseApiUrl}player/${player.player_id}`, {
      headers: headers,
      body: JSON.stringify(!isHuman)
    },
   );
    const result = await response.json();
    console.log(response, "response");
    console.log(result, "result");
  } catch (error) {
    console.error(error);
  }

  // TODO: remove below alert, when changeState works.
  alert("Sorry - Not implemented - We are working on it");
}
</script>

<template>
  <div v-if="game" class="container mx-auto">
    <h1 class="text-center my-5">Admin - Manage Game</h1>
    <div class="text-center font-bold text-2xl my-4">{{ game.name }}</div>
    <div class="bg-gray-100 bg-opacity-60 p-3 grid md:grid-cols-3">
      <section class="p-3">
        <!-- Description column -->
        <h2 class="text-center mb-2">Game Description</h2>
        <div class="m-auto w-full">
          <div>
            <textarea
              v-model.trim="game.game_description"
              class="block w-full h-60 p-2"
            />
          </div>
        </div>
      </section>
      <section class="p-3">
        <!-- Players column -->
        <h2 class="text-center mb-2">Players</h2>
        <div class="text-center m-2 text-red-500 font-semibold bg-black">
          Administering players is not implemented
        </div>
        <PlayerList v-bind:players="players" @change-state="changeState" />
      </section>
      <section class="p-3">
        <!-- Missions column -->
        <h2 class="text-center mb-2">Missions</h2>
        <div class="text-center m-2 text-red-500 font-semibold bg-black">
          Missions are not implemented.
        </div>
      </section>
    </div>

    <!-- Buttons -->
    <div class="flex items-start justify-evenly w-full lg:w-2/6 mx-auto mt-2">
      <router-link
        to="/admin"
        class="px-10 py-1 bg-red-500 rounded text-gray-800 hover:text-gray-600"
        >Cancel</router-link
      >
      <button
        @click="changeGameInfo(game.game_id)"
        class="bg-blue-500 rounded px-10 py-1 text-gray-200"
      >
        Update Game
      </button>
    </div>
  </div>
</template>
