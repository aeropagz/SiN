import { createStore } from "vuex";

export default createStore({
  state: {
    loggedIn: false,
  },
  getters: {
    isLoggedIn(state) {
      return state.loggedIn;
    },
  },
  mutations: {
    login(state) {
      state.loggedIn = true;
    },
    logout(state) {
      state.loggedIn = false;
    },
  },
  actions: {},
  modules: {},
});
