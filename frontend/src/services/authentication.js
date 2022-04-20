import axios from "axios";
const API_URL = "https://localhost:8080/api/user/";
class AuthService {
  login(user) {
    return axios.post(API_URL + "login", user).then((response) => {
      if (response.headers.mysession) {
        localStorage.setItem("session", response.headers.mysession);
      }
      return response.data;
    });
  }
  register(user) {
    return axios
      .post(API_URL + "register", user)
      .then((response) => {
        return response;
      })
      .catch((error) => {
        console.log(error.response.data);
        return error.response;
      });
  }
}
export default new AuthService();
