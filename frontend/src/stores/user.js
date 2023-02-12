import {defineStore} from "pinia";
import {userInfo} from "../http/index";

export const useUserStore = defineStore({
  id: "user",
  state: () => {
    let data = localStorage.getItem("userInfo");
    let userName = "";
    let email = "";

    if (data) {
      data = JSON.parse(data);
      userName = data.name;
      email = data.email;
    }

    return {
      userName: userName,
      email: email
    }
  },
  getters: {
    getUserName: (state) => state.userName,
    getEmail: (state) => state.email,
  },
  actions: {
    setUserName(name) {
      this.userName = name;
    },
    setEmail(email) {
      this.email = email
    },
    async getUserDetails() {
      const details = await userInfo();

      localStorage.setItem("userInfo", JSON.stringify(details.data))

      this.userName = details.data.name;
      this.email = details.data.email;
    },
  },
});
