<script>
import { useCounterStore } from "@/stores/counter.js";
import MdlButton from "@/components/MdlButton.vue";

export default {
  name: "PiniaDemoView",
  setup() {
    const counterStore = useCounterStore();
    return {
      counterStore,
    };
  },
  components: {
    MdlButton,
  },
  methods: {
    increment() {
      this.counterStore.increment();
    },
    decrement() {
      this.counterStore.decrement();
    },
  },
};
</script>
<template>
  <div>
    <h1>Pinia Demo</h1>
    <p>Counter: {{ counterStore.doubleCount }}</p>
    <MdlButton @click="increment">Increment</MdlButton>
    <MdlButton @click="decrement">Decrement</MdlButton>
  </div>
</template>
