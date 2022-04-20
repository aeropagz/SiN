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
    return axios.post(API_URL + "register", user).then((response) => {
      console.log(response);
      return response.status;
    });
  }
}
export default new AuthService();
