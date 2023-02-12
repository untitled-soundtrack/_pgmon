<script>
import { useMainStore } from "@/stores/mainstore.js";
import { useAuthStore } from "@/stores/auth.js";
import { useUserStore } from "@/stores/user.js";
import emitter from "../tools/emitter";
import Snackbar from "./Snackbar.vue";


export default {
  setup() {
    const mainStore = useMainStore();
    const userStore = useUserStore();
    const authStore = useAuthStore();
    return {
      mainStore,
      userStore,
      authStore
    }
  },
  components: {
    Snackbar
  },

  methods: {
    goBack() {
        this.$router.go(-1)
    },
    logout(){
        //TODO remove from pinia
        this.mainStore.$reset();
        this.userStore.$reset();
        this.authStore.$reset();
        localStorage.removeItem("authToken");
        this.$router.push({name: 'login'});
        emitter.emit("snackbar", "Logged Out!")
    },
  },
};
</script>

<template>
  <div class="header-bar">
    <div class="header-section-left">
      <button v-if="this.authStore.getToken" @click="goBack" id="btn-back" class="backBtnLink">
        <div class="svgWrapp">
          <svg class="svg-icon" viewBox="0 0 20 20">
            <path d="M3.24,7.51c-0.146,0.142-0.146,0.381,0,0.523l5.199,5.193c0.234,0.238,0.633,0.064,0.633-0.262v-2.634c0.105-0.007,0.212-0.011,0.321-0.011c2.373,0,4.302,1.91,4.302,4.258c0,0.957-0.33,1.809-1.008,2.602c-0.259,0.307,0.084,0.762,0.451,0.572c2.336-1.195,3.73-3.408,3.73-5.924c0-3.741-3.103-6.783-6.916-6.783c-0.307,0-0.615,0.028-0.881,0.063V2.575c0-0.327-0.398-0.5-0.633-0.261L3.24,7.51 M4.027,7.771l4.301-4.3v2.073c0,0.232,0.21,0.409,0.441,0.366c0.298-0.056,0.746-0.123,1.184-0.123c3.402,0,6.172,2.709,6.172,6.041c0,1.695-0.718,3.24-1.979,4.352c0.193-0.51,0.293-1.045,0.293-1.602c0-2.76-2.266-5-5.046-5c-0.256,0-0.528,0.018-0.747,0.05C8.465,9.653,8.328,9.81,8.328,9.995v2.074L4.027,7.771z"></path>
          </svg>
        </div>
        <div for="btn-back" class="mdl-tooltip mdl-tooltip--large">Back</div>
      </button>
    </div>

    <img src="../assets/images/pokemon-logo.svg" alt="">

    <div class="header-section-right ">
      <RouterLink v-if="this.authStore.getToken" class="pokedexLink" to="/pokedex" >
        <div id="pokedex_Link" class="svgWrapp">
          <svg viewBox="0 0 20 20">
							<path d="M8.627,7.885C8.499,8.388,7.873,8.101,8.13,8.177L4.12,7.143c-0.218-0.057-0.351-0.28-0.293-0.498c0.057-0.218,0.279-0.351,0.497-0.294l4.011,1.037C8.552,7.444,8.685,7.667,8.627,7.885 M8.334,10.123L4.323,9.086C4.105,9.031,3.883,9.162,3.826,9.38C3.769,9.598,3.901,9.82,4.12,9.877l4.01,1.037c-0.262-0.062,0.373,0.192,0.497-0.294C8.685,10.401,8.552,10.18,8.334,10.123 M7.131,12.507L4.323,11.78c-0.218-0.057-0.44,0.076-0.497,0.295c-0.057,0.218,0.075,0.439,0.293,0.495l2.809,0.726c-0.265-0.062,0.37,0.193,0.495-0.293C7.48,12.784,7.35,12.562,7.131,12.507M18.159,3.677v10.701c0,0.186-0.126,0.348-0.306,0.393l-7.755,1.948c-0.07,0.016-0.134,0.016-0.204,0l-7.748-1.948c-0.179-0.045-0.306-0.207-0.306-0.393V3.677c0-0.267,0.249-0.461,0.509-0.396l7.646,1.921l7.654-1.921C17.91,3.216,18.159,3.41,18.159,3.677 M9.589,5.939L2.656,4.203v9.857l6.933,1.737V5.939z M17.344,4.203l-6.939,1.736v9.859l6.939-1.737V4.203z M16.168,6.645c-0.058-0.218-0.279-0.351-0.498-0.294l-4.011,1.037c-0.218,0.057-0.351,0.28-0.293,0.498c0.128,0.503,0.755,0.216,0.498,0.292l4.009-1.034C16.092,7.085,16.225,6.863,16.168,6.645 M16.168,9.38c-0.058-0.218-0.279-0.349-0.498-0.294l-4.011,1.036c-0.218,0.057-0.351,0.279-0.293,0.498c0.124,0.486,0.759,0.232,0.498,0.294l4.009-1.037C16.092,9.82,16.225,9.598,16.168,9.38 M14.963,12.385c-0.055-0.219-0.276-0.35-0.495-0.294l-2.809,0.726c-0.218,0.056-0.351,0.279-0.293,0.496c0.127,0.506,0.755,0.218,0.498,0.293l2.807-0.723C14.89,12.825,15.021,12.603,14.963,12.385"></path>
						</svg>
        </div>
        <div for="pokedex_Link" class="mdl-tooltip mdl-tooltip--large">Pokédex</div>
      </RouterLink>

      <RouterLink v-if="this.authStore.getToken" to="/userinfo" class="userInfoLink">
        <div id="user" class="svgWrapp">
          <svg viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg"><title/><g data-name="user people person users man" id="user_people_person_users_man"><path d="M23.74,16.18a1,1,0,1,0-1.41,1.42A9,9,0,0,1,25,24c0,1.22-3.51,3-9,3s-9-1.78-9-3a9,9,0,0,1,2.63-6.37,1,1,0,0,0,0-1.41,1,1,0,0,0-1.41,0A10.92,10.92,0,0,0,5,24c0,3.25,5.67,5,11,5s11-1.75,11-5A10.94,10.94,0,0,0,23.74,16.18Z"/><path d="M16,17a7,7,0,1,0-7-7A7,7,0,0,0,16,17ZM16,5a5,5,0,1,1-5,5A5,5,0,0,1,16,5Z"/></g></svg>
        </div>
        <div for="user" class="mdl-tooltip mdl-tooltip--large">Userinfo</div>
      </RouterLink>
      <button v-if="this.authStore.getToken" @click="logout" id="btn-logout">
        <div id="logout" class="svgWrapp">
          <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><g><path d="M0 0h24v24H0z" fill="none"/><path d="M12 22C6.477 22 2 17.523 2 12S6.477 2 12 2a9.985 9.985 0 0 1 8 4h-2.71a8 8 0 1 0 .001 12h2.71A9.985 9.985 0 0 1 12 22zm7-6v-3h-8v-2h8V8l5 4-5 4z"/></g></svg>
        </div>
        <!-- <div for="logout" class="mdl-tooltip mdl-tooltip--large">Logout</div> -->
      </button>
    </div>
    <Snackbar></Snackbar>
  </div>
</template>

<style scoped lang="scss">
@import "../assets/scss/components/headerBar";
</style>


