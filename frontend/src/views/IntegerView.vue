<template>
  <div>
    <input type="number" name="integer" id="integer" v-model="input.number" />
    <button type="button" @click="sendInt()">Save</button>
  </div>
  <h3>Numbers</h3>
  <div>
    {{ numbers }}
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "IntegerView",
  data() {
    return {
      input: {
        number: 0,
      },
      numbers: [],
    };
  },
  methods: {
    async sendInt() {
      let res = await axios.post("https://localhost:8080/api/number/", {
        number: this.input.number,
      });
      console.log(res.data);
      this.numbers = res.data.numbers;
    },
  },
};
</script>
