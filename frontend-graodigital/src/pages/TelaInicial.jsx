import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ModalAgricultor from "../components/ModalAgricultor";
import "../styles/telaInicial.css";
import logo from "../../src/assets/logo-graodigital.png";
import { getAgricultores } from "../api/agricultorApi";

function TelaInicial() {
  const [openModal, setOpenModal] = useState(false);
  const [agricultores, setAgricultores] = useState([]);

  // Recupera usuário logado
  const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));
  const nomeUsuario = usuario?.nome || "Usuário";

  const navigate = useNavigate();

  function sair() {
    localStorage.removeItem("usuarioLogado");
    navigate("/");
  }

  // Carrega a lista de agricultores no início
  useEffect(() => {
    carregarAgricultores();
  }, []);

  async function carregarAgricultores() {
    try {
      const lista = await getAgricultores();
      setAgricultores(lista);
    } catch (error) {
      console.error("Erro ao carregar agricultores:", error);
    }
  }

  return (
    <div className="container-geral">
      {/* Header */}
      <header className="header">
        <img src={logo} alt="logo" className="logo" />
        <span className="bemvindo">Bem-vindo(a), {nomeUsuario}</span>
        <button className="btn-sair" onClick={sair}>SAIR</button>
      </header>

      {/* Painéis superiores */}
      <div className="cards">
        <div className="card">
          <h4>Próximas Visitas</h4>
          <p className="numero verde">3 agendadas</p>
          <span>Esta semana</span>
        </div>

        <div className="card">
          <h4>Meus Agricultores</h4>
          <p className="numero verde">{agricultores.length} total</p>
          <span>Registrados no sistema</span>
        </div>

        <div className="card">
          <h4>Acompanhamento Pendente</h4>
          <p className="numero laranja">6 Agricultores</p>
          <span>Esperando visita</span>
        </div>

        <div className="card">
          <h4>Visitas Realizadas</h4>
          <p className="numero verde">18 visitas</p>
          <span>Este mês</span>
        </div>
      </div>

      {/* Abas */}
      <div className="abas">
        <button className="aba">Geral</button>
        <button className="aba ativa">Agricultores</button>
        <button className="aba">Acompanhamento</button>
        <button className="aba">Relatório</button>
        <button className="aba">Compras</button>
      </div>

      {/* Tabela */}
      <div className="bloco-tabela">
        <div className="topo-tabela">
          <h3>Meus Agricultores</h3>
          <button className="btn-registrar" onClick={() => setOpenModal(true)}>
            👥 Registrar
          </button>
        </div>

        <table className="tabela">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Local</th>
              <th>Cultura</th>
              <th>CPF</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>
            {agricultores.length > 0 ? (
              agricultores.map((a) => (
                <tr key={a.id}>
                  <td>{a.idAgricultor}</td>
                  <td>{a.nome}</td>
                  <td>{a.localEndereco}</td>
                  <td>{a.tipoCultura}</td>
                  <td>{a.CPF}</td>
                  <td>
                    <span className="status laranja">Pendente</span>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6" style={{ textAlign: "center", opacity: 0.7 }}>
                  Nenhum agricultor encontrado.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {openModal && (
        <ModalAgricultor
          fechar={() => {
            setOpenModal(false);
            carregarAgricultores(); // recarrega após cadastro
          }}
        />
      )}
    </div>
  );
}

export default TelaInicial;
