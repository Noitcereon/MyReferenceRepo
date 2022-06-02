<script setup lang="ts">
import { onMounted } from "vue";
import { useRoute } from "vue-router";
import GameDetailNavigation from "../components/GameDetailNavigation.vue";
import GameDetailSquadDetails from "../components/GameDetailSquadDetails.vue";
import GameDetailSquadRegistration from "../components/GameDetailSquadRegistration.vue";

const route = useRoute();

onMounted(() => {
});
</script>

<template>
  <div
    id="gameDetailsWrapper"
    class="grid lg:grid-cols-[250px_minmax(400px,_1fr)_300px]"
  >
    <section id="sidebarMenu">
      <GameDetailNavigation />
    </section>
    <main class="grow p-5">
      <router-view></router-view>
    </main>
    <section id="rightSidebar" class="bg-gray-600 hidden lg:block text-gray-200 p-4">
      <h2>Squad</h2>
      <div>
        <!-- TODO: Shows if the user is NOT registered as a player -->
        <p>
          Register as a player to view available squads (not currently
          implemented)
        </p>
      </div>
      <div v-if="false">
        <!-- Temporary v-if=false to hide this div -->
        <!-- TODO: Shows if registered as a player, but not to a squad -->
        <GameDetailSquadRegistration />

        <!-- TODO: Show squad details if registered to a squad in this game -->
        <GameDetailSquadDetails />
      </div>
    </section>
  </div>
</template>

<style scoped></style>
