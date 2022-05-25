<script setup lang="ts">
import { IPlayer } from "../models/IPlayer";

const props = defineProps<{
  players: IPlayer[];
}>();

// The below emits const is here for documentation purposes.
const emits = defineEmits<{
  (e: "changeState", player: IPlayer): void;
}>();

</script>

<template>
  <table class="w-full text-center my-4 mx-3 bg-gray-100">
    <template v-if="players.length > 0">
      <thead class="bg-black text-gray-200">
        <tr>
          <th>Player ID</th>
          <th>State</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="player of props.players" :key="player.player_id">
          <td>{{ player.player_id }}</td>
          <td v-if="player.is_human === 'y'">Human</td>
          <td v-else>Zombie</td>
          <td>
            <button class="text-blue-500 font-semibold" @click="$emit('changeState', player)">
              Switch State
            </button>
          </td>
        </tr>
      </tbody>
    </template>
    <div v-else class="text-center rounded-md">No players registered.</div>
  </table>
</template>
