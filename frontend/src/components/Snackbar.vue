<script>
import emitter from "../tools/emitter";
import {upgradeElement} from "../tools";

export default {
  name: "Snackbar",
  methods: {
    showSnackbar(message) {
      this.$refs.snackbar.MaterialSnackbar.showSnackbar({
        message: message,
        timeout: 3000
      });
    },
  },
  async mounted() {
    await upgradeElement(this.$refs.snackbar);
    emitter.on("snackbar", (data) => {this.showSnackbar(data); console.log(data)})
  },
};
</script>
<template>
  <div
    aria-live="assertive"
    aria-atomic="true"
    aria-relevant="text"
    class="mdl-snackbar mdl-js-snackbar"
    ref="snackbar">
    <div
      class="mdl-snackbar__text" style="font-size: 1vw">
    </div>
    <button type="button" class="mdl-snackbar__action"></button>
  </div>
</template>
