import axios from "axios";

export const issueApi = axios.create({
  baseURL: "http://localhost:8082",
});

export const workerApi = axios.create({
  baseURL: "http://localhost:8083",
});

export const authApi = axios.create({
    baseURL: "http://localhost:8081/auth"
});

issueApi.interceptors.request.use(config => {

    const token = localStorage.getItem("token");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});

// Automatically attach JWT to Worker Service requests
workerApi.interceptors.request.use(config => {

    const token = localStorage.getItem("token");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});