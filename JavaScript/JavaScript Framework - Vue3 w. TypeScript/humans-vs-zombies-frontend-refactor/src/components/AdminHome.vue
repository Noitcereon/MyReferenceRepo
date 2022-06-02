<script setup lang="ts">
import { computed, inject, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const store = useStore();

const loggedIn = computed(() => store.state.loginModule.loggedIn);
const games = computed(() => store.state.gameListModule.games);
const keycloak: any = inject("kc");

onMounted(() => {
  store.dispatch("gameListModule/fetchGames");

  keycloak.onAuthSuccess = function () {
    if (loggedIn.value == false) {
      store.dispatch("loginModule/loginSuccess", keycloak);
    }
  };
});
</script>

<template>
  <div class="container mx-auto">
    <!-- Link to create game component -->
    <h1 class="text-2xl font-bold my-3 mx-3 text-center">Admin - Home</h1>
    <div class="flex justify-center m-4">
      <router-link
        class="px-5 py-2 bg-blue-500 text-center mx-auto text-gray-200 w-full lg:w-64 rounded-xl"
        :to="{ name: 'create-game' }"
        >Create New Game</router-link
      >
    </div>

    <!-- GameList -->
    <div class="grid gap-5 grid-cols-1 lg:grid-cols-3">
      <section
        class="bg-red-200 p-3 max-h-40 text-center hover:bg-gradient-to-br from-red-200 to-red-700 rounded-xl"
        v-for="game in games"
        :key="game.game_id"
      >
        <div class="m-auto w-3/4 lg:w-full">
          <h3 class="text-center">{{ game.name }}</h3>
          <div class="text-center">
            <label class="font-semibold"> Game state: </label>
            <span>{{ game.game_state }}</span>
          </div>
          <div class="text-center truncate">
            <label class="font-semibold"> Game description: </label>
            <span>{{ game.game_description }}</span>
          </div>
          <div class="text-center my-2">
            <router-link
              class="px-3 py-2 text-center my-5 bg-blue-500 text-gray-200 rounded-xl"
              :to="`/admin/manage-game/${game.game_id}`"
              >Manage Game</router-link
            >
          </div>
        </div>
        <!-- Todo: pass id as param -->
      </section>
    </div>
  </div>
</template>
