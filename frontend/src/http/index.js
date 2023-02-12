import axios from "axios";
import {useAuthStore} from "../stores/auth";
import emitter from "../tools/emitter";
import { useMainStore } from "../stores/mainstore";

const backend = axios.create({
  baseURL: "/api/",
  timeout: 1000,
});

const api = axios.create({
  baseURL: "https://lbartner-01.media.fhstp.ac.at:4430/api",
});
api.interceptors.request.use(
  (config) => {
    //get pinia instance
    const authStore = useAuthStore();
    if (authStore.getToken) {
      config.headers["Authorization"] = `Bearer ${authStore.getToken}`;
    }
    return config
  }, (error) => {
    return Promise.reject(error)
  }
);

api.interceptors.response.use(function (response) {
    return response;
  },
  function (error){
  emitter.emit("api-error", error);
  return Promise.reject(error);
  }
)


export function getSightings({north, east, south, west}) {
  const coordinates = [north, east, south, west].join(",");
  return api.get(`/sightings/map/${coordinates}`);
}


export async function addPokemon(pokedexId) {
  let userPosition = useMainStore().getUserPosition
  userPosition = JSON.parse(JSON.stringify(userPosition))
  await api.post("/sightings/", ({
    "position": userPosition,
    "pokedex-id": pokedexId
  }))
}
export function getPokemon(pokedexId) {
  return api.get(`/pokemon/de/${pokedexId}`)
}
export function getAllPokemons() {
  return api.get(`/pokemon/de/`)
}


export async function register({name, email, password}) {
  console.log("ROUTE", name, email, password)
  return await api.post("/users/register", {
    name: name,
    email: email,
    password: password
  });
}

export async function login({ email, password }) {
  return await api.post("/users/login", {
    email: email,
    password: password
  });
}

export async function userInfo() {
  return await api.get("/users/details");
}

export async function changeUserInfo({ name, email, password, passwordNew }) {
  return await api.patch("/users/details", { name, email, password, passwordNew });
}


