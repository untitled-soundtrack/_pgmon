<script>
import {useMainStore} from "@/stores/mainstore.js";
import {getSightings} from '../http/index.js';
import VueMultiselect from 'vue-multiselect';
import BottomNavbar from "@/components/BottomNavbar.vue";

export default {
  setup() {
    const mainStore = useMainStore();
    return {
      mainStore,
    }
  },
  components: {
    VueMultiselect,
    BottomNavbar
  },
  data() {
    console.log(this.mainStore.getSightings);
    console.log(this.mainStore.sightings);
    //TODO replace with api call
    return {
      sightings: this.mainStore.getSightings,
      selected: null,
      options: ['list', 'of', 'options']
    }
  },
  created() {
  },
  methods: {
    pokemonIcon(pokedexId) {
      return `/pokemon-icons/${pokedexId.toString()}.png`;
    }
  },
  computed: {}
};
</script>

<template>
  <main>
    <div class="sightingsWrapper" v-if="mainStore.sightings.length">
      <h2 class="sightingsCountMsg"><span class="sightingsCount">{{ mainStore.sightings.length }}</span> Pokemon in
        deiner Nähe gefunden</h2>
      <ul class="mdl-list">
        <li class="mdl-list__item" v-for="sighting in mainStore.sightings" :key="sighting['sighting-id']">
          <img :src="pokemonIcon(sighting['pokedex-id'])"/>
          <span class="mdl-list__item-primary-content">
                  <span class="list-id">#{{ sighting['pokedex-id'] }}</span> <span
            class="list-name">{{ sighting.details.name }}</span>
                </span>
        </li>
      </ul>
    </div>
    <h2 v-else class="noListEntry">Keine Pokemon in deiner Nähe gefunden</h2>
  </main>
  <BottomNavbar></BottomNavbar>

</template>

<style src="vue-multiselect/dist/vue-multiselect.css"></style>
<style scoped lang="scss">
@import "../assets/scss/list.scss";
</style>
