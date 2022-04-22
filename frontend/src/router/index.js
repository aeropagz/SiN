import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import IntegerView from "../views/IntegerView.vue";
import OrderDetailView from "../views/OrderDetail.vue";
import { isAuthenticated } from "@/services/localStorage.js";


const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
  },
  {
    path: "/detail/:id",
    name: "detail",
    component: OrderDetailView,
    props: true
  },
  {
    path:"/number",
    name:"number",
    component: IntegerView
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// eslint-disable-next-line no-unused-vars
router.beforeEach(async (to, from) => {
  if (
    // is authenticated if localStorage contains session id
    !isAuthenticated() &&
    // ❗️ Avoid an infinite redirect
    !["login", "register", "number"].includes(to.name)
  ) {
    // redirect the user to the login page
    return { name: "login" };
  }
});



export default router;
