<script setup lang="ts">
import { onMounted, computed } from "vue";
import { useStore } from "vuex";

const store = useStore();
const games = computed(() => store.state.gameListModule.games);

const loggedIn = computed(() => store.state.loginModule.loggedIn);

onMounted(() => {
  store.dispatch("gameListModule/fetchGames");
});
</script>

<template>
  <main>
    <h1 class="text-2xl font-bold my-3 mx-3 text-center">Home</h1>
    <!-- GameList -->
    <div class="grid bg-gray-100 gap-5 lg:grid-cols-3">
      <!-- Game Item END -->
      <div
        class="bg-red-200 p-3 text-center"
        v-for="(game, gameIndex) of games[1]"
      >
        <!-- BUG fixed: In the resultset we get an empty array as games[0] and the resulting array as games[1]! -->
        <h2 class="text-lg font-semibold">{{ game.name }}</h2>

        <!-- Game State -->
        <p class="font-light py-3">
          {{ game.game_state }}
        </p>

        <!-- Details button -->
        <router-link
          v-show="loggedIn"
          :to="`/game/${game.game_id}`"
          class="bg-blue-400 text-gray-800 hover:bg-blue-700 px-5 py-1 rounded"
          >View details</router-link
        >
      </div>
      <!-- Game Item END -->
    </div>
    <!-- Game List END  -->
  </main>
</template>
