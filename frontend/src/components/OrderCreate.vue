<template>
  <div class="d-flex flex-column justify-content-center">
    <h3>Send an order!</h3>
    <div class="w-50 mx-auto">
      <div class="form-outline form-white form-floating mb-4">
        <input
          type="text"
          id="good"
          class="form-control"
          placeholder="good"
          v-model="input.good"
        />
        <label class="form-label text-black" for="good">
          Name of Good</label
        >
      </div>
      <div class="form-outline form-white form-floating mb-4">
        <input
          type="number"
          id="amount"
          class="form-control"
          placeholder="amount"
          v-model="input.amount"
        />
        <label class="form-label text-black" for="amount"> Amount</label>
      </div>
      <button class="btn btn-secondary" @click="createOrder()">Order</button>
    </div>
  </div>
  <hr>
  <OrderOverview :key="trigger" />
</template>

<script>
import orderService from "@/services/orders.js";
import { getSession } from "@/services/localStorage.js";
import OrderOverview from "@/components/OrderOverview.vue";

export default {
  name: "OrderCreate",
  components: {
    OrderOverview,
  },
  data: () => {
    return {
      input: {
        good: "",
        amount: 0,
      },
      trigger: 0,
    };
  },
  methods: {
    createOrder() {
      const uid = getSession();
      const payload = {
        customer: uid,
        ...this.input,
      };
      orderService.createOrder(payload).then((response) => {
        console.log(response.data);
        this.orders = response.data;
        this.trigger++;
      });
    },
  },
};
</script>
