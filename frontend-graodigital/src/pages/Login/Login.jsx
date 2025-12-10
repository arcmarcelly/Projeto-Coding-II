import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUsuario } from "../../api/usuarioApi";
import "./Login.css";
import logo from "../../assets/logo-graodigital.png";

function Login() {
  const [tipoUsuario, setTipoUsuario] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!tipoUsuario) {
      setErro("Selecione o tipo de usuário!");
      return;
    }

    try {
      const usuario = await loginUsuario(email, senha, tipoUsuario);
      console.log("LOGIN OK:", usuario);

      // Salva no localStorage
      localStorage.setItem("usuarioLogado", JSON.stringify(usuario));

      setErro("");

      // Navega e força reload para atualizar o App.js
      navigate("/agricultores");
      window.location.reload();

    } catch (err) {
      console.log("LOGIN ERRO:", err);
      setErro("Email, senha ou tipo de usuário incorretos!");
    }
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <img src={logo} alt="Painel" className="logo" />
        
        <p className="subtexto">Selecione seu perfil para acessar o sistema</p>

        <div className="perfil-container">
          {["ADMINISTRATIVO", "TECNICO", "ASSOCIACAO"].map((item) => (
            <button
              key={item}
              className={`perfil-btn ${tipoUsuario === item ? "ativo" : ""}`}
              onClick={() => setTipoUsuario(item)}
            >
              {item.toLowerCase()}
            </button>
          ))}
        </div>

        <form className="form-login" onSubmit={handleSubmit}>
          <input
            type="email"
            placeholder="Email"
            className="input"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            type="password"
            placeholder="Senha"
            className="input"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
          />

          <button type="submit" className="btn-acessar">
            Acessar
          </button>
        </form>

        {erro && <p className="msg-erro">{erro}</p>}
      </div>
    </div>
  );
}

export default Login;
