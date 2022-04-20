import axios from "axios";
import { getSession } from "./localStorage.js";

const API_URL = "https://localhost:8080/api/order/";

class OrderService {
  createOrder(order) {
    return axios.post(API_URL, order).then((response) => {
      return response.data;
    });
  }
  getOrders() {
    const session = getSession();

    return axios
      .get(API_URL + "all/" + session)
      .then((response) => {
        return response;
      })
      .catch((error) => {
        console.log(error.response.data);
        return error.response;
      });
  }
}
export default new OrderService();
