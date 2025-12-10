import { useState } from "react";
import "../styles/modalAgricultor.css";
import { addAgricultor } from "../api/agricultorApi";

function ModalAgricultor({ fechar }) {
  const [form, setForm] = useState({
    nome: "",
    telefone: "",
    local: "",
    cpf: "",
    cultura: "",
  });

  const [loading, setLoading] = useState(false);
  const [mensagemErro, setMensagemErro] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validar = () => {
    if (!form.nome.trim()) return "Informe o nome do agricultor.";
    if (!form.telefone.trim()) return "Informe o telefone.";
    if (!form.local.trim()) return "Informe o endereço/local.";
    if (!form.cpf.trim()) return "Informe o CPF.";
    if (!form.cultura.trim()) return "Informe o tipo de cultura.";
    return null;
  };

  const registrar = async () => {
    setMensagemErro("");

    const erroValidacao = validar();
    if (erroValidacao) {
      setMensagemErro(erroValidacao);
      return;
    }

    // PEGAR USUÁRIO LOGADO CORRETAMENTE
    const usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));
    const idUsuario = usuarioLogado?.idUsuario;

    if (!idUsuario) {
      setMensagemErro("Usuário não autenticado. Faça login novamente.");
      return;
    }

    // PAYLOAD FINAL: exatamente como backend espera
    const payload = {
      nome: form.nome,
      telefone: form.telefone,
      localEndereco: form.local,
      CPF: form.cpf,
      tipoCultura: form.cultura,
      usuarioId: idUsuario
    };

    try {
      setLoading(true);
      await addAgricultor(payload);

      alert("Agricultor cadastrado com sucesso!");
      fechar();
      window.location.reload();

    } catch (err) {
      console.error("Erro ao cadastrar agricultor:", err);
      setMensagemErro(
        err?.response?.data
          ? `Erro: ${JSON.stringify(err.response.data)}`
          : "Erro ao conectar-se ao servidor."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal">

        <button className="fechar" onClick={fechar} disabled={loading}>
          ✖
        </button>

        <h2>Registrar Agricultor</h2>
        <p className="subtitulo">Adicionar um novo agricultor</p>

        {mensagemErro && (
          <div
            style={{
              background: "#ffe6e6",
              color: "#900",
              padding: 10,
              borderRadius: 8,
              marginBottom: 12,
            }}
          >
            {mensagemErro}
          </div>
        )}

        <div className="linha">
          <div className="campo">
            <label>Nome*</label>
            <input
              name="nome"
              placeholder="Nome do agricultor"
              value={form.nome}
              onChange={handleChange}
            />
          </div>

          <div className="campo">
            <label>Telefone*</label>
            <input
              name="telefone"
              placeholder="Nº para contato"
              value={form.telefone}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="linha">
          <div className="campo">
            <label>Local*</label>
            <input
              name="local"
              placeholder="Rua, Bairro, CEP..."
              value={form.local}
              onChange={handleChange}
            />
          </div>

          <div className="campo">
            <label>CPF*</label>
            <input
              name="cpf"
              placeholder="XXX.XXX.XXX-XX"
              value={form.cpf}
              onChange={handleChange}
            />
          </div>
        </div>

        <div className="campo">
          <label>Tipo de Cultura*</label>
          <input
            name="cultura"
            placeholder="Ex.: Milho, Feijão, Tomate..."
            value={form.cultura}
            onChange={handleChange}
          />
        </div>

        <button
          className="btn-enviar"
          onClick={registrar}
          disabled={loading}
        >
          {loading ? "Registrando..." : "Registrar"}
        </button>
      </div>
    </div>
  );
}

export default ModalAgricultor;
