import api from "./api";

export const loginUser = (form) => {
    return api.post("/users/login", form);
};

export const registerUser = (form) => {
    return api.post("/users/register", form);
};