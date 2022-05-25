<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup
import Login from "./components/Login.vue";


</script>

<template>
<!-- Styling for backgroundWrapper is in index.css -->
<div id="backgroundWrapper"></div>
  <header class="bg-gray-800 text-white px-5 py-3 grid md:grid-cols-3 grid-cols-2">
    <div class="hidden md:block">
      <!-- Empty div to fill space -->
    </div>
    <!-- TODO: move the below nav into a component + conditional display based on login status -->
    <nav class="m-auto">
      <ul class="space-x-5 text-center">
        <li class="listItem">
          <router-link to="/">Home</router-link>
        </li>
        <li class="listItem">
          <router-link to="/admin">Administration</router-link>
        </li>
      </ul>
    </nav>
    <div class="ml-7 md:ml-auto">
      <Login />
    </div>
  </header>

  <!-- Where Views are displayed (based on the current route) -->
  <router-view></router-view>
  <footer class="text-center py-2 fixed bottom-0 inset-x-0 bg-gray-800">
    <router-link to="/icon-attribution">Icon attribution</router-link>
  </footer>
</template>

<style scoped>
.listItem {
  display: inline;
  color: rgb(102, 157, 246);
  font-weight: 700;
}
</style>
