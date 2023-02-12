<script>
import useVuelidate from "@vuelidate/core";
import {useAuthStore} from "../stores/auth";
import {useUserStore} from "../stores/user";
import inputText from "../components/InputText.vue";
import {
  required,
  helpers,
  email
} from "@vuelidate/validators";
import MdlButton from "@/components/MdlButton.vue";
import { login } from "../http/index";
import emitter from "../tools/emitter";
import Snackbar from "../components/Snackbar.vue";

export default {
  setup() {
    const authStore = useAuthStore()
    const userStore = useUserStore()
    return {
      userStore,
      authStore,
      v$: useVuelidate(),
    };
  },
  components: {
    MdlButton,
    inputText,
    Snackbar
  },
  data() {
    return {
      email: "",
      password: "",
      user: {},
    };
  },
  validations() {
    return {
//Für die required fields wird hier validiert ob email und passwort in Ordnung sind.
      email: {
        required: helpers.withMessage("Email is a required field ", required),
        email: helpers.withMessage("Please make sure it is an email address", email),
        $autoDirty: true,
      },
      password: {
        required: helpers.withMessage(
          "Password is a required field", required),
        $autoDirty: true,
      },
    };
  },
  methods: {
    async submit() {
      const isFormCorrect = await this.v$.$validate();
      if (!isFormCorrect) return;
      const user = await login({email: this.email, password: this.password});

      console.log("", user.data["auth-token"]);
      this.authStore.setToken(user.data["auth-token"]);
      localStorage.authToken = user.data["auth-token"];
      await this.userStore.getUserDetails();
      console.log("user", user);
      this.$router.push("map");
      emitter.emit("snackbar", "User successfully logged in")
    },
  },
};
</script>

<template>
  <main>
  <div>
    <form id="login" class="form" novalidate @submit.stop.prevent="submit">
      <h3>LOGIN</h3>

      <input class="mdl-textfield__input inputText"  v-model="email" type="text" placeholder="Enter Email" />
      <br />
      <span v-if="v$.email.$errors">
        <ul>
          <li v-for="error in v$.email.$errors" :key="error.$message" class="errormsg">
            {{ error.$message }}
          </li>
        </ul>
      </span>

      <input class="mdl-textfield__input inputText" style="width: 300px" v-model="password" type="password" placeholder="Enter Password" />
      <br />
      <span v-if="v$.password.$errors">
        <ul>
          <li v-for="error in v$.password.$errors" :key="error.$message" class="errormsg">
            {{ error.$message }}
          </li>
        </ul>
      </span>
      <router-link to="register" class="routerLink">You aren't registered? Register</router-link>
      <div>
        <MdlButton type="raised" color="primary" class="button">Login</MdlButton>
      </div>
    </form>
  </div>
    <Snackbar></Snackbar>
  </main>
</template>

<style scoped lang="scss">
  main::before{
    content:'';
    display: block;
    width: 100%;
    height: 80px;
  }
</style>
<style lang="scss"> @import "../assets/scss/components/autheticate";</style>

