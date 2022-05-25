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
import { ref, onMounted, computed } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";

const store = useStore();
const route = useRoute();

// part of alternative solution
// let inputBiteCode = computed(() => store.state.biteCodeModule.player.biteCode);

// retrive user from state
const user = computed(() => store.state.loginModule.user);

// get game id from url
const routeGameId = Number.parseInt(route.params.gameId[0]);

// get the player object (associated with the user) for this game.
const player = user.value.players.find((player: any) => {
  return player.game_id === routeGameId;
});

onMounted(() => {
  // #region alternative solution to retriving bite code
  // let activeUser = {
  //   currentUser: computed(() => store.state.loginModule.user),
  //   keycloakToken: computed(() => store.state.loginModule.keycloakToken),
  //   gameID: Number.parseInt(route.params.gameId[0]),
  // };
  // console.log(activeUser);
  // Retrieve a unique human friendly bite code from API (given to zombie player)
  // store.dispatch("biteCodeModule/retrieveBiteCode", activeUser);
  // #endregion
});
</script>

<template>
  <div class="text-center">
    <h1 class="text-2xl font-bold mb-4">Bite Code:</h1>
    <span
      class="animateGlow text-5xl font-extrabold tracking-wider border-2 rounded border-black border-opacity-30"
    >
      {{ player.bite_code }}</span
    >
  </div>
</template>

<style scoped>
.animateGlow {
  /* -webkit & -moz are present for compatability reasons with older browsers */
  -webkit-animation: glow 1s ease-in-out infinite alternate;
  -moz-animation: glow 1s ease-in-out infinite alternate;
  animation: phasingGlow 4s ease-in-out infinite alternate;
}
@keyframes phasingGlow {
  100% {
    color: #050;
  }
  50% {
    color: #595;
  }
  0% {
    color: #f33;
  }
}
</style>
