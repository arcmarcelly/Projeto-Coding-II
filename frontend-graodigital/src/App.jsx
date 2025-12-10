import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import TelaInicial from "./pages/TelaInicial";

function App() {
  const usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));

  return (
    <Router>
      <Routes>
        {/* Tela de login */}
        <Route path="/" element={<Login />} />

        {/* Tela inicial após login */}
        <Route
          path="/agricultores"
          element={
            usuarioLogado ? <TelaInicial /> : <Navigate to="/" />
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
