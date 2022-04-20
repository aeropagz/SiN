<template>
  <div class="home">
    <input type="number" name="integer" id="integer" v-model="input.number">
    <button type="button" @click="sendInt()">
     Save
    </button>
  </div>

  
</template>

<script>
import axios from "axios";
const API_URL = "https://localhost:8080/api/number/";

export default {
  name: 'IntegerView',
  data() {
    return {
      input: {
        number: 0,
      },
    };
  },
  methods: {
    /*async*/ sendInt() {
      //let res = await axios.post("https://localhost:8080/api/number/", {number:this.number});
      //console.log(res.data);
      return axios
      .post(API_URL, {number:this.number})
      .then((response) => {
        return response;
      })
      .catch((error) => {
        console.log(error.response.data);
        return error.response;
      });
    },
  },
}

</script>