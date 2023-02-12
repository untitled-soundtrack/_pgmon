<script>
import MdlButton from "../components/MdlButton.vue";

export default {
  components: {
    MdlButton
  }
}
</script>

<template>
  <div class="about">
    <!-- <MdlButton type="elevated" color="blue" icon="" disabled=""></MdlButton> -->
    <MdlButton></MdlButton>

  </div>
</template>


<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
