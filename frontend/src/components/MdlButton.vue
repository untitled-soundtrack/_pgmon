<script>
import { upgradeElement } from "@/tools";
export default {
  props: {
    type: {
      type: String,
      validator: (value) =>
        ["raised", "fab", "mini-fab", "icon"].includes(value),
    },
    color: {
      type: String,
      validator: (value) => ["primary", "colored", "accent"].includes(value)
    },
    icon: String,
    disabled: {type: Boolean},
},
 computed: {
    buttonType(){
      let val = "";
      if (this.type) {
        val = `mdl-button--${this.type}`;
      }
      if (this.type === "mini-fab"){
        val += " mdl-button--fab";
      }
      return val;
    },
   buttonColor() {
      let val = "";
      if (this.color) {
        val = `mdl-button--${this.color}`;
      }
      return val;
   }
  },

  mounted() {
    upgradeElement(this.$refs.button);
  },
  };
</script>

<template>
  <button class="mdl-button mdl-js-button mdl-js-ripple-effect"
    ref="button" :disabled="disabled" :class="[buttonType, buttonColor]">
    <i v-if="icon" class="material-icons">{{icon}}</i>
    <slot>submit</slot>
  </button>
</template>
