<template>
  <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <div class="mb-md-5 mt-md-4 pb-5">
                <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                <p class="text-white-50 mb-5">
                  Please enter your login and password!
                </p>

                <div class="form-outline form-white form-floating mb-4">
                  <input
                    type="text"
                    id="typeUserX"
                    class="form-control form-control-lg"
                    placeholder="username"
                    v-model="input.username"
                  />
                  <label class="form-label text-black" for="typeUserX">
                    Username
                  </label>
                </div>

                <div class="form-outline form-white form-floating mb-4">
                  <input
                    type="password"
                    id="typePasswordX"
                    class="form-control form-control-lg"
                    placeholder="password"
                    v-model="input.password"
                  />
                  <label class="form-label text-black" for="typePasswordX">
                    Password
                  </label>
                </div>

                <p class="small mb-5 pb-lg-2">
                  <a class="text-white-50" href="#!">Forgot password?</a>
                </p>

                <button
                  class="btn btn-outline-light btn-lg px-5"
                  type="button"
                  @click="login()"
                >
                  Login
                </button>
              </div>

              <div>
                <p class="mb-0">
                  Don't have an account?
                  <router-link to="/register">Sign Up</router-link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import authService from "../services/authentication.js";
export default {
  name: "LoginView",
  data() {
    return {
      input: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService.login(this.input).then((data) => {
        console.log(data);
        this.$router.push("/");
      });
    },
  },
};
</script>

<style></style>
