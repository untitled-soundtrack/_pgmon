import { createApp } from "vue";
import { createPinia } from "pinia";
import VueGoogleMaps from '@fawmi/vue-google-maps'
import "material-design-lite/material";

import App from "./App.vue";
import router from "./router";
import { useAuthStore } from "./stores/auth";

const app = createApp(App);

app.config.globalProperties.$sightings = null;

app.use(createPinia());

app.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyAG68azW4ulQQ4MpCVi1NeP8mUEYUGAxf4'
  },
});

app.use(router);
app.mount("#app");

const authStore = useAuthStore();
if(localStorage.authToken){
  authStore.setToken(localStorage.authToken);
}
