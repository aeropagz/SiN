<template>
  <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <div class="mb-md-5 mt-md-4 pb-5">
                <h2 class="fw-bold mb-2 text-uppercase">Register</h2>
                <p class="text-white-50 mb-5">
                  Please enter your username, email and password!
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
                    type="email"
                    id="typeEmailX"
                    class="form-control form-control-lg"
                    placeholder="email"
                    v-model="input.email"
                  />
                  <label class="form-label text-black" for="typeEmailX">
                    Email
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

                <button
                  class="btn btn-outline-light btn-lg px-5"
                  type="button"
                  @click="register()"
                >
                  Register
                </button>
              </div>

              <div>
                <p class="mb-0">
                  You already have an account?
                  <router-link to="/login">Sign In</router-link>
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
  name: "RegisterView",
  data() {
    return {
      input: {
        username: "",
        email: "",
        password: "",
      },
    };
  },
  methods: {
    register() {
      authService.register(this.input).then((response) => {
        console.log(response);
        if (response.status === 200) {
          this.$router.push("/login");
        } else if (response.status === 403) {
          console.log(response.data);
        }
      });
    },
  },
};
</script>

<style></style>
