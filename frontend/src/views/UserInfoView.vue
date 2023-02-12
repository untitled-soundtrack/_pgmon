<script>
import router from "@/router";
import useVuelidate from "@vuelidate/core";
import { useAuthStore } from "../stores/auth";
import { useUserStore } from "../stores/user";
import {
  required,
  helpers,
  email,
  minLength,
  sameAs
} from "@vuelidate/validators";
import MdlButton from "@/components/MdlButton.vue";
import Snackbar from "../components/Snackbar.vue";
import emitter from "../tools/emitter";
import { changeUserInfo } from "../http/index";

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
    Snackbar
  },
  created() {
    console.log("USER STORE;", this.userStore.getUserName, this.userStore.getEmail);
  },
  data() {
    return {
      name: this.userStore.getUserName,
      email: this.userStore.getEmail,
      oldPassword: "",
      password: "",
      password2: "",
      user: {}
    };
  },
  validations() {
    return {
      //Für die required fields wird hier validiert ob email und passwort in Ordnung sind.
      name: {
        required: helpers.withMessage(
          "Name is a required field.",
          required
        ),
        minLength: helpers.withMessage(
          "Name must be at least 4 characters long",
          minLength(4)
        ),
        $autoDirty: true,
      },
      email: {
        required: helpers.withMessage("Email is a required field.", required),
        email: helpers.withMessage("Please enter a valid email address.", email),
        $autoDirty: true,
      },
      oldPassword: {
        required: helpers.withMessage(
          "Old Password is a required field.",
          required
        ),
        minLength: helpers.withMessage(
          "Old Password must be at least 8 characters long",
          minLength(8)
        ),
        autoDirty: true,
      },
      password: {
        required: helpers.withMessage(
          "Password is a required field.",
          required
        ),
        minLength: helpers.withMessage(
          "Password must be at least 8 characters long",
          minLength(8)
        ),
        autoDirty: true,
      },
      password2: {
        required: helpers.withMessage(
          "Password Confirm is a required field",
          required
        ),
        sameAsPassword: helpers.withMessage(
          "Both Passwords must be same",
          sameAs(this.password)
        ),
        $autoDirty: true,
      },
    };
  },
  methods: {
    async submit() {
      const isFormCorrect = await this.v$.$validate();
      if (!isFormCorrect) return;

      console.log("DATA: ", this.name, this.email, this.oldPassword, this.password);
      const data = await changeUserInfo({ name: this.name, email: this.email, password: this.oldPassword, passwordNew: this.password });
      const userDetails = JSON.parse(data.config.data)
      console.log("USER DETAILS:", userDetails);
      await this.userStore.getUserDetails();
      emitter.emit("snackbar", "Data successfully changed")
      return userDetails;
    },
  },
};
</script>

<template>
  <main>
    <div class="container">
      <form id="changeForm" class="form" novalidate @submit.stop.prevent="submit">
        <input class="mdl-textfield__input inputText" v-model="name" type="text" placeholder="Enter Name" />
        <br />
        <span v-if="v$.name.$errors">
          <ul>
            <li v-for="error in v$.name.$errors" :key="error.$message">
              {{ error.$message }}
            </li>
          </ul>
        </span>

        <input class="mdl-textfield__input inputText" v-model="email" type="text" placeholder="Enter Email" />
        <br />
        <span v-if="v$.email.$errors">
          <ul>
            <li v-for="error in v$.email.$errors" :key="error.$message">
              {{ error.$message }}
            </li>
          </ul>
        </span>

        <input class="mdl-textfield__input inputText" v-model="oldPassword" type="password" placeholder="Old Password" />
        <br />
        <span v-if="v$.oldPassword.$errors">
          <ul>
            <li v-for="error in v$.oldPassword.$errors" :key="error.$message">
              {{ error.$message }}
            </li>
          </ul>
        </span>

        <input class="mdl-textfield__input inputText" v-model="password" type="password" placeholder="Enter new Password" />
        <br />
        <span v-if="v$.password.$errors">
          <ul>
            <li v-for="error in v$.password.$errors" :key="error.$message">
              {{ error.$message }}
            </li>
          </ul>
        </span>

        <input class="mdl-textfield__input inputText" v-model="password2" type="password" placeholder="Confirm new Password" />
        <br />
        <span v-if="v$.password2.$errors">
          <ul>
            <li v-for="error in v$.password2.$errors" :key="error.$message">
              {{ error.$message }}
            </li>
          </ul>
        </span>
      <MdlButton class="button" type="raised" color="primary" form="changeForm">Change</MdlButton>
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

<style lang="scss">
@import "../assets/scss/components/autheticate";
</style>
