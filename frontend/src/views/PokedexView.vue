<script>
import { useMainStore } from "@/stores/mainstore.js";
import { getSightings } from "../http/index.js";
import BottomNavbar from "@/components/BottomNavbar.vue";
import inputText from "../components/InputText.vue";

export default {
  setup() {
    const mainStore = useMainStore();
    return {
      mainStore,
    };
  },
  components: {
    BottomNavbar,
    inputText,
  },
  data() {
    return {
      name: "",
      pType: "",
      selected: "Alle Pokétypen", //Für die Filterung nach Poke-Typ
    };
  },
  created() {
    this.mainStore.showAllPokemons();
  },
  methods: {
    pokemonIcon(pokedexId) {
      return `/pokemon-icons/${pokedexId.toString()}.png`;
    },

    // Hilfsfunktion für die Ermittlung des gesuchten MDL-Icons
    pTypeIcon(pType) {
      switch (pType) {
        case "Gift":
          return "dangerous";
        case "Wasser":
          return "water";
        case "Pflanze":
          return "grass";
        case "Gestein":
          return "landscape";
        case "Wasser":
          return "water";
        case "Eis":
          return "icecream";
        case "Flug":
          return "flight";
        case "Kampf":
          return "swords";
        case "Elektro":
          return "bolt";
        case "Stahl":
          return "fitness_center";
        case "Psycho":
          return "psychology";
        case "Normal":
          return "circle";
        case "Boden":
          return "footprint";
        case "Geist":
          return "face_5";
        case "Feuer":
          return "local_fire_department";
        case "Fee":
          return "magic_button";
        case "Drache":
          return "icecream";
        case "Käfer":
          return "bug_report";
        case "Unlicht":
          return "clear_night";
        default:
          return "";
      }
    },
  },
  computed: {},
};
</script>

<template>
  <main>
    <!-- Suchfeld für den Pokemon-Namen -->
    <input
      class="mdl-textfield__input inputText"
      v-model="name"
      type="text"
      placeholder="Tippe den Namen des gewünschten Pokémons ein"
    />

    <!-- Filterung nach Poke-Typen -->
    <select class="mdl-textfield__input" v-model="selected">
      <option v-for="type in this.mainStore.types">
        {{ type }}
      </option>
    </select>

    <!-- Auflistung der gefundenen Pokemon-Details als MDL-Card -->
    <ul class="mdl-list">
      <li v-for="pokmon in this.mainStore.pokemon" :key="pokmon.pokedexId">
        <div
          v-if="
            selected === 'Alle Pokétypen' ||
            selected === pokmon.types[0] ||
            selected === pokmon.types[1]
          "
        >
          <div
            class="mdl-card mdl-shadow--6dp"
            v-if="pokmon.name.toLowerCase().includes(name.toLowerCase())"
          >
            <div class="mdl-card__media poke_media">
              <img class="poke_icon" :src="pokemonIcon(pokmon['pokedex-id'])" />
            </div>
            <div
              class="mdl-card__title-text poke_name"
              style="text-align: right"
            >
              #{{ pokmon["pokedex-id"] }} {{ pokmon.name }}
            </div>

            <div class="mdl-card__subtitle-text poke_groesse">
              Größe: {{ pokmon.height }}
            </div>

            <div class="mdl-card__subtitle-text poke_gewicht">
              Gewicht: {{ pokmon.weight }}
            </div>

            <div class="mdl-card__subtitle-text typ">
              Typ:
              <span
                v-for="type in pokmon.types"
                class="mdl-card__subtitle-text"
              >
                <i class="material-symbols-outlined poke_typ" id="pokeTyp">
                  {{ pTypeIcon(type) }}</i
                >
              </span>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </main>
  <BottomNavbar></BottomNavbar>
</template>

<style scoped lang="scss">
@import "../assets/scss/card.scss";
</style>
