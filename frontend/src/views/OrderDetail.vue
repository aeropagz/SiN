<template>
  <div>
    {{ this.order }}
  </div>
</template>

<script>
import orderService from "@/services/orders.js";

export default {
  name: "OrderDetailView",
  props: {
    id: Number,
  },
  data: () => {
    return {
      order: {},
    };
  },
  methods: {
    getDetailOrder() {
      orderService.getDetailOrder(this.id).then((response) => {
        console.log(response);
        if (response.status == 200) {
          this.order = response.data;
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
