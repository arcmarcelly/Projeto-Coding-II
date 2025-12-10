import axios from "axios";

const API_URL = "http://localhost:8081/usuarios";

export const loginUsuario = async (email, senha, tipoUsuario) => {
  try {
    const response = await axios.post(`${API_URL}/login`, {
      email,
      senha,
      tipoUsuario
    });

    return response.data;
  } catch (error) {
    console.error("Erro no login:", error.response?.data || error);
    throw error;
  }
};
