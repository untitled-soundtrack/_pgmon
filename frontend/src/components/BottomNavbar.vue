<template>
  <footer>
  <div class="bottom-navbar">
      <nav>
        <button id="addPokemon" class="btn-pokeball">
          <RouterLink to="/addpokemon" class="pokeballlink">
          <img src="../assets/images/pokeball.svg" alt="pokeball button">
          </RouterLink>
        </button>
        <div for="addPokemon" class="mdl-tooltip mdl-tooltip--large mdl-tooltip--top">Add Sighting</div>
        <ul>
          <li><RouterLink to="/map">Map</RouterLink></li>
          <li><RouterLink to="/list">List</RouterLink></li>
        </ul>
      </nav>
    </div>
  </footer>
</template>

<style scoped lang="scss">
@import "../assets/scss/components/bottomNav";
</style>



