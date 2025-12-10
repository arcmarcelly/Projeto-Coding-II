import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login/Login";
import TelaInicial from "./pages/TelaInicial";

function App() {
  const usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));

  return (
    <Router>
      <Routes>
        {/* Tela de login */}
        <Route path="/" element={<Login />} />

        {/* Tela de agricultores (tela inicial) */}
        <Route
          path="/agricultores"
          element={usuarioLogado ? <TelaInicial /> : <Navigate to="/" />}
        />
      </Routes>
    </Router>
  );
}

export default App;
