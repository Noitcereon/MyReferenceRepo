<script lang="ts" setup>
import { inject, ref } from "vue";
import { useStore } from "vuex";

const store = useStore();
const keycloak: any = inject("kc");

let inputGameName = ref("");
let inputGameState = ref("");
let inputGameDescription = ref("");

const createGameWithInfo = async () => {
  let newGame = {
    name: inputGameName.value,
    gameState: inputGameState.value,
    gameDescription: inputGameDescription.value,
    token: keycloak.token,
  };

  await store.dispatch("gameListModule/createGame", newGame);
};
</script>

<template>
  <h1 class="text-center">Create Game</h1>
  <section class="p-3 w-full md:w-2/3 lg:w-2/6 mx-auto">
    <form @submit.prevent="createGameWithInfo">
      <div class="mx-auto">
        <label class="font-semibold"> Name </label>
        <input
          class="block w-full border-2 border-black"
          required
          v-model.trim="inputGameName"
        />
      </div>
      <div class="mx-auto mt-2">
        <label class="font-semibold"> Game Description: </label>
        <textarea
          class="block rounded w-full border-2 border-black"
          required
          v-model.trim="inputGameDescription"
        />
      </div>

      <!-- Buttons -->
      <div class="flex items-start justify-evenly mx-auto mt-2">
        <router-link
          to="/admin"
          class="px-10 py-1 bg-red-500 rounded text-gray-800 hover:text-gray-600"
          >Cancel</router-link
        >
        <button
          type="submit"
          class="bg-blue-500 rounded px-10 py-1 text-gray-200"
        >
          Create New Game
        </button>
      </div>
    </form>
  </section>
</template>
