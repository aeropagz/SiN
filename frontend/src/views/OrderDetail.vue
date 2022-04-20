<template>
  <div>
    <h2>Order</h2>
    <div class="w-50 mx-auto">
      <div class="d-flex justify-content-between">
        <span>Order Id:</span> <span>{{ this.order.did }}</span>
      </div>
      <div class="d-flex justify-content-between">
        <span>Good:</span> <span>{{ this.order.good }}</span>
      </div>
      <div class="d-flex justify-content-between">
        <span>Amount:</span> <span>{{ this.order.amount }}</span>
      </div>
    </div>
    <hr />
    <h2>Customer</h2>
    <div class="w-50 mx-auto">
      <div class="d-flex justify-content-between">
        <span>User Id:</span> <span>{{ this.customer.id }}</span>
      </div>
      <div class="d-flex justify-content-between">
        <span>Username:</span> <span>{{ this.customer.username }}</span>
      </div>
      <div class="d-flex justify-content-between">
        <span>Email:</span> <span>{{ this.customer.email }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import orderService from "@/services/orders.js";

export default {
  name: "OrderDetailView",
  props: {
    id: String,
  },
  data: () => {
    return {
      order: {},
      customer: {},
    };
  },
  methods: {
    getDetailOrder() {
      orderService.getDetailOrder(this.id).then((response) => {
        console.log(response);
        if (response.status == 200) {
          this.order = response.data;
          this.customer = response.data.customer;
        } else {
          console.error(response.data);
        }
      });
    },
  },
  mounted() {
    this.getDetailOrder();
  },
};
</script>

<style></style>
