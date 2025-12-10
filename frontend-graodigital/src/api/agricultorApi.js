import axios from "axios";

const API_URL = "http://localhost:8081/api/agricultores";

// Lista todos
export const getAgricultores = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

// Cadastra
export const addAgricultor = async (agricultor) => {
  const response = await axios.post(API_URL, agricultor);
  return response.data;
};

// Atualiza
export const updateAgricultor = async (id, agricultor) => {
  const response = await axios.put(`${API_URL}/${id}`, agricultor);
  return response.data;
};

// Deleta
export const deleteAgricultor = async (id) => {
  await axios.delete(`${API_URL}/${id}`);
};
