<template>
  <h3>Your Orders</h3>
  <div class="d-flex flex-row flex-wrap">
    <div
      v-for="order in orders"
      :key="order.did"
      class="m-3"
      @click="openDetailView(order.did)"
    >
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">{{ order.good }}</h5>
          <hr />
          <div class="col">
            <div class="row">
              <p class="card-text">Order Id: {{ order.did }}</p>
            </div>
            <div class="row">
              <p class="card-text">Amount: {{ order.amount }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import orderService from "@/services/orders.js";

export default {
  name: "OrderOverview",
  data: () => {
    return {
      orders: [],
    };
  },
  methods: {
    getOrders() {
      orderService.getOrders().then((response) => {
        console.log(response.data);
        if (response.status == 200) {
          this.orders = response.data;
          console.log(this.data);
        } else {
          console.error(response.data);
        }
      });
    },
    openDetailView(id) {
      this.$router.push("/detail/" + id);
    },
  },
  mounted() {
    this.getOrders();
  },
};
</script>
<style scoped lang="scss">
ul {
  list-style: none;
}

.card:hover {
  transform: scale(1.2);
}
</style>
