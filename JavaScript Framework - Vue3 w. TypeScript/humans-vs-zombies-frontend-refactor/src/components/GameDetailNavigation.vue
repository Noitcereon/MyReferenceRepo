<script setup lang="ts">
import { computed, Ref, ref } from "vue";
import { useRoute } from "vue-router";
import { useStore } from "vuex";
import { IPlayer } from "../models/IPlayer";
let isMenuClosed = ref(true);
function toggleMenu() {
  isMenuClosed.value = !isMenuClosed.value;
}

const store = useStore();
const route = useRoute();

const routeGameId: number = Number.parseInt(route.params.gameId[0]);
// const user = computed(() => store.state.loginModule.user);

// retrieve player object from user (is undefined if user is not registered to the game)
const player: Ref<IPlayer> | undefined = computed(() =>
  store.state.loginModule.user.players.find(
    (player: IPlayer) => player.game_id === routeGameId
  )
);
</script>

<template>
  <!-- Toggle menu -->
  <button
    class="lg:hidden w-12 h-12 absolute right-2"
    style="top: 15px"
    @click="toggleMenu"
  >
    <i class="fa fa-bars text-gray-200 text-4xl active:text-gray-300"></i>
  </button>
  <nav
    :class="{ hidden: isMenuClosed }"
    class="w-3/4 lg:w-full lg:block absolute right-0 lg:static"
  >
    <ul class="text-center">
      <li class="p-4 bg-gray-700 opacity-95">
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'gameTitle' }"
          @click="toggleMenu"
        >
          Game Info
        </router-link>
      </li>
      <li class="p-4 bg-gray-700 opacity-95" v-if="player === undefined">
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'gameRegister' }"
          @click="toggleMenu"
        >
          Register
        </router-link>
      </li>
      <li class="p-4 bg-gray-700 opacity-95">
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'gameMap' }"
          @click="toggleMenu"
          >Map</router-link
        >
      </li>
      <li
        class="p-4 bg-gray-700 opacity-95"
        v-if="player !== undefined && player.is_human === 'y'"
      >
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'biteCode' }"
          @click="toggleMenu"
          >Bite Code</router-link
        >
      </li>
      <li
        class="p-4 bg-gray-700 opacity-95"
        v-if="player !== undefined && player.is_human === 'n'"
      >
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'biteCodeEntry' }"
          @click="toggleMenu"
          >Bite Code Entry</router-link
        >
      </li>
      <li class="p-4 bg-gray-700 opacity-95" v-if="player">
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'gameChat' }"
          @click="toggleMenu"
          >Chat</router-link
        >
      </li>
      <li class="p-4 bg-gray-700 opacity-95" v-if="player">
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'gameSquadDetails' }"
          @click="toggleMenu"
          >Squad Details</router-link
        >
      </li>
      <li class="p-4 bg-gray-700 opacity-95" v-if="player">
        <router-link
          class="text-gray-200 inline-block w-full"
          :to="{ name: 'gameSquadRegister' }"
          @click="toggleMenu"
          >Squad Register</router-link
        >
      </li>
    </ul>
  </nav>
</template>

<style scoped>
.router-link-exact-active {
  font-weight: bold;
}
</style>
