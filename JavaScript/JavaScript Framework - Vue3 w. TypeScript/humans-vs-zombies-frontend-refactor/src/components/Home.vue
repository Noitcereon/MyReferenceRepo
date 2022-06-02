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
    <div class="container mx-auto px-4">
      <div class="grid gap-4 lg:grid-cols-3">
        <!-- GameList -->
        <div
          class="bg-lime-700 p-3 text-center hover:bg-gradient-to-br from-lime-700 to-zinc-700 rounded-xl"
          v-for="game of games"
          :key="game.game_id"
        >
          <!-- BUG fixed: In the resultset we get an empty array as games[0] and the resulting array as games[1]! -->
          <h2 class="text-lg font-semibold">{{ game.name }}</h2>

          <!-- Game State -->
          <p class="font-light py-3">state: {{ game.game_state }}</p>

          <!-- Details button -->
          <router-link
            v-show="loggedIn"
            :to="`/game/${game.game_id}`"
            class="bg-blue-400 text-gray-800 hover:bg-blue-700 px-5 py-1 rounded-md"
            >View details</router-link
          >
          <!-- Game Item END -->
        </div>
        <!-- Game List END  -->
      </div>
    </div>
  </main>
</template>
