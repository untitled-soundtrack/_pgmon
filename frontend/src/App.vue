<script setup>
import { RouterLink, RouterView } from "vue-router";
import BottomNavbar from "./components/BottomNavbar.vue";
import HeaderBar from "./components/HeaderBar.vue";

</script>

<template>
    <header>
      <HeaderBar></HeaderBar>
    </header>
    <RouterView></RouterView>
</template>

<style lang="scss">
@import "./assets/scss/general.scss";
</style>
