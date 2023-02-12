<script>
import useVuelidate from "@vuelidate/core";
import { email, helpers, minLength, required, sameAs } from "@vuelidate/validators";
import MdlButton from "@/components/MdlButton.vue";
import { register } from "../http/index";
import emitter from "../tools/emitter";
import Snackbar from "../components/Snackbar.vue";

export default {
  setup() {
    return {
      v$: useVuelidate(),
    };
  },
  components: {
    MdlButton,
    Snackbar
  },
  data() {
    return {
      name: "",
      email: "",
      password: "",
      password2: "",
      user: {},
    };
  },
  validations() {
    return {
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
      console.log(this.email, this.password, this.name)
      const user = await register({ name: this.name, email: this.email, password: this.password });
      console.log("user", user);
      this.$router.push("login");
      emitter.emit("snackbar", "User successfully created")
      return user;
    },
  },
};

</script>

<template>
  <main>

    <div>
      <form id="register" class="form" novalidate @submit.stop.prevent="submit">
        <h3>REGISTER</h3>
        <input class="mdl-textfield__input inputText" v-model="name" type="text" placeholder="Enter Name" />
        <br />
        <span v-if="v$.name.$errors">
          <ul>
            <li v-for="error in v$.name.$errors" :key="error.$message" class="errormsg">
              {{ error.$message }}
            </li>
          </ul>
        </span>

        <input class="mdl-textfield__input inputText" v-model="email" type="text" placeholder="Enter Email" />
        <br />
        <span v-if="v$.email.$errors">
          <ul>
            <li v-for="error in v$.email.$errors" :key="error.$message" class="errormsg">
              {{ error.$message }}
            </li>
          </ul>
        </span>
        <input class="mdl-textfield__input inputText" v-model="password" type="password" placeholder="Enter Password" />
        <br />
        <span v-if="v$.password.$errors">
          <ul>
            <li v-for="error in v$.password.$errors" :key="error.$message" class="errormsg">
              {{ error.$message }}
            </li>
          </ul>
        </span>

        <input class="mdl-textfield__input inputText" v-model="password2" type="password"
          placeholder="Confirm Password" />
        <br />
        <span v-if="v$.password2.$errors">
          <ul>
            <li v-for="error in v$.password2.$errors" :key="error.$message" class="errormsg">
              {{ error.$message }}
            </li>
          </ul>
        </span>
        <MdlButton type="raised" color="primary" form="register" class="button">Register</MdlButton>
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

