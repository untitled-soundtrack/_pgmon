import { defineStore } from "pinia";
import {getSightings, getPokemon, getAllPokemons, addPokemon} from '../http/index.js';

export const useMainStore = defineStore("mainstore", {
  state: () => ({
    userPosition: {
      lat: 48.20889357827148,
      lng: 16.37346836903637,
    },
    bounds: {},
    sightings: [],
    counter: 0,
    pokemon: [],
    types: ["Alle Pokétypen"]
  }),
  getters: {
    getUserPosition: (state) => state.userPosition,
    getBounds: (state) => state.bounds,
    getSightings: (state) => state.sightings,
    getAllPokemons: (state) => state.pokemon,
    getAllTypes: (state) => state.types,
  },
  actions: {
    changeUserPosition(lat, lng){
      if(lat && lng){
        this.userPosition.lat = lat;
        this.userPosition.lng = lng;
      }
    },
    updateBounds(north, east, south, west){
      this.bounds = {
        north: north,
        east: east,
        south: south,
        west: west,
      }

    },
    async updateSightings(){
      const sightingsResp = await getSightings(this.bounds);
      const sightings = sightingsResp.data;
      //get details
      for (const sighting of sightings) {
        if(sighting["pokedex-id"]){
          const pokemonData = (await getPokemon(sighting ["pokedex-id"])).data;
          if(pokemonData){
            sighting.details = pokemonData;
          }
        }
      }
      const filteredSightings = sightings.filter((sighting) => {
        return sighting["pokedex-id"] != null;
      });

      this.sightings = filteredSightings;
    },
    async showAllPokemons(){
      const currentpokemon = this.pokemon
      if (currentpokemon.length === 0) {
        this.pokemon = ((await getAllPokemons()).data)
        for (const pok of this.pokemon) {
          for (const typ of pok.types)
          {
            if (!this.types.includes(typ))
            {
              this.types.push(typ);
            }
          }
        }
      }
    },
    async addNewSighting(pokedexId){
      await addPokemon(pokedexId)
    }
  },
});
