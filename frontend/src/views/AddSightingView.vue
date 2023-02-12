<script>
import MdlButton from "@/components/MdlButton.vue";
import {useMainStore} from "../stores/mainstore";
import VueMultiselect from 'vue-multiselect'
import emitter from "../tools/emitter";
import Snackbar from "../components/Snackbar.vue";


export default {
  setup() {
    const mainStore = useMainStore();
    return {
      mainStore,

    };
  },
  components: {
    MdlButton,
    VueMultiselect,
    Snackbar
  },
  data() {
    return {
      name: "",
      selectedId: null,
      selected: null,
    };
  },
  created() {
    this.mainStore.showAllPokemons()
  },

  methods: {
    optionSelected(value) {
      this.selectedId = value['pokedex-id']
      this.name = value['name']
    },
    async submit(){
      if (this.selectedId) {
        await this.mainStore.addNewSighting(this.selectedId)
        emitter.emit("snackbar", "Sighting  "  + this.name + "   added")
      }
    }
  },
}

</script>

<template>
  <main>
    <div>
      <form id="sighting" class="form" novalidate @submit.stop.prevent="submit">
        <VueMultiselect
          v-model="selected"
          :options="this.mainStore.pokemon" label="name"
          @select="optionSelected">
        </VueMultiselect>
        <MdlButton @click="submit" type="raised" color="primary" form="register" class="button">Add Sighting</MdlButton>
      </form>
    </div>
    <Snackbar></Snackbar>
  </main>
</template>
<style scoped lang="scss">
main::before {
  content: '';
  display: block;
  width: 100%;
  height: 80px;
}
</style>

<style src="vue-multiselect/dist/vue-multiselect.css"></style>


