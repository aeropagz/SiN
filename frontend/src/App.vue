<template>
  <nav>
    <router-link to="/">Home</router-link>|
    <router-link to="/number">Number</router-link>
    <button
      class="btn btn-outline-secondary btn-sm float-end"
      @click="logout()"
    >
      Logout
    </button>
  </nav>
  <div class="container">
    <router-view />
  </div>
</template>
<script>
import { deleteSession, isAuthenticated } from "@/services/localStorage";

export default {
  methods: {
    logout() {
      deleteSession();
      this.$router.push("/login");
    },
  },
  computed: {
    isLoggedIn() {
      return isAuthenticated();
    },
  },
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
