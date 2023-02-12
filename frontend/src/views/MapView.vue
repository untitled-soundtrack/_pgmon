<script>
import {useMainStore} from "@/stores/mainstore.js";
import {getSightings, getPokemon} from '../http/index.js';
import BottomNavbar from "@/components/BottomNavbar.vue";


export default {
  setup() {
    const mainStore = useMainStore();
    return {
      mainStore,
    }
  },
  data() {
    return {
      bounds: {},
      pokemon: [
        {
          name: "Pikachu",
          "pokedex-id": 25,
        },
        {
          name: "Raichu",
          "pokedex-id": 26,
        },
      ],
      pokeomIcon: [
        {
          name: "Pikachu",
          "pokedex-id": 25,
        }
      ],
      options: {
        mapId: 'e68a6dc39814e62a', //Selbst erstellter Map-Stil
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        zoomControl: false,
      },
    };
  },
  created() {
    this.setPosition();
  },
  components: {
    BottomNavbar
  },
  methods: {
    setPosition() {
      if (navigator.geolocation) {
        navigator.geolocation.watchPosition((position) => {
          this.mainStore.changeUserPosition(position.coords.latitude, position.coords.longitude);
        })
      }
    },

    getSightings() {
      this.mainStore.getSightings();
    },

    async boundsChanged(bounds) {
      const ne = bounds.getNorthEast();
      const sw = bounds.getSouthWest();

      const boundsObj = {
        north: ne.lat(),
        east: ne.lng(),
        south: sw.lat(),
        west: sw.lng()
      }
      const sightings = await getSightings(boundsObj);
      //update bounds
      this.mainStore.updateBounds(ne.lat(), ne.lng(), sw.lat(), sw.lng());
      //update sightings based on new bounds
      await this.mainStore.updateSightings();

    },

    pokemonIcon(pokedexId) {
      return `/pokemon-icons/${pokedexId.toString()}.png`;
    }

  },
  computed: {}
};
</script>

<template>
  <main>
    <GMapMap @bounds_changed="boundsChanged" :center="mainStore.getUserPosition" :zoom="15" :options="options">
      <GMapMarker :z-index="9999" :position="mainStore.getUserPosition" :clickable="true" :draggable="true"/>
      <GMapMarker v-for="sighting in mainStore.sightings" :z-index="sighting['sighting-id']"
                  :key="sighting['sighting-id']" :position="sighting.position"
                  :icon="{url: pokemonIcon(sighting['pokedex-id'])}">

      </GMapMarker>
    </GMapMap>
  </main>
  <BottomNavbar></BottomNavbar>

</template>

<style scoped lang="scss">
@import "../assets/scss/map.scss";
</style>
