<script>
export default {
  props: {
    type: {
      type: String,
      validator: (value) =>
        ["text", "password"].includes(value),
    },
    label: {
      type: String,
      required: true,
    },
    disabled: {type: Boolean},
},
 computed: {
  },

  };
</script>

<template>
  <div class="input-field">
    <label>
      {{ label }}
      <input :type="type" v-model="value" :disabled="disabled"/>
    </label>
  </div>
</template>
