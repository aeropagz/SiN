<template>
  <div class="home container">
    <OrderCreate />
  </div>
</template>

<script>
// @ is an alias to /src
import OrderCreate from "@/components/OrderCreate.vue";

export default {
  name: "HomeView",
  components: {
    OrderCreate,
  },
};
</script>
