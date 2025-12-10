# GrãoDigital - Sistema de Aquisição e Gestão de Sementes do IPA

[![React](https://img.shields.io/badge/React-17.0.2-blue?logo=react)](https://reactjs.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange?logo=java)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-Academic-lightgrey)](#)

---

## Descrição do Projeto
O **GrãoDigital** é um sistema web **fullstack** desenvolvido para a **aquisição e gestão de sementes** do **Instituto Agronômico de Pernambuco (IPA)**.  

O sistema permite:  
- Cadastro e gerenciamento de agricultores;  
- Gestão de sementes e tipos de culturas;  
- Controle de transportes e entregas;  
- Visualização de relatórios e status das operações;  

O projeto foi desenvolvido com foco em **facilidade de uso**, **rastreabilidade** e **eficiência operacional**.

----

## Tecnologias Utilizadas

**Frontend:**  
- React.js  
- JavaScript (ES6)  
- HTML5 & CSS3  
- React Router  
- Axios  

**Backend:**  
- Java 17  
- Spring Boot  
- Spring Data JPA  
- Hibernate ORM  
- Banco de dados relacional (H2 ou configurável)  

**Ferramentas Adicionais:**  
- Postman  
- Git/GitHub  

---

## Entidades Principais

| Entidade     | Descrição                                                                 |
|--------------|---------------------------------------------------------------------------|
| Agricultor   | Representa agricultores. Campos: `id`, `nome`, `telefone`, `localEndereco`, `CPF`, `tipoCultura`, `usuarioId`. |
| Semente      | Representa sementes gerenciadas. Campos: `id`, `nome`, `quantidade`, `tipoCultura`. |
| Transporte   | Controla entregas de sementes. Campos: `id`, `nomeMotorista`, `dataHoraSaida`, `dataHoraChegada`, `agricultorId`. |
| Usuário      | Representa usuários do sistema. Campos: `idUsuario`, `nome`, `login`, `senha`. |

---

## Estrutura do Projeto

- `TelaInicial.jsx` – Tela principal do sistema.  
- `ModalAgricultor.jsx` – Modal para cadastro de agricultores.  
- `AgricultorApi.js` – Comunicação frontend/backend via Axios.  
- Controllers no backend gerenciam endpoints REST.  
- Repositórios Spring Data JPA fazem persistência no banco.  

---

## Como Rodar o Projeto

### Backend
1. Abra o backend na IDE (Eclipse, IntelliJ, VS Code).  
2. Configure o banco de dados em `application.properties`.  
3. Rode a aplicação (`Spring Boot Run`) na porta **8081**.  

### Frontend
1. Entre na pasta `frontend/`.  
2. Execute `npm install` para instalar dependências.  
3. Execute `npm start` para iniciar o React na porta **3001**.  

O sistema estará disponível em [http://localhost:3001](http://localhost:3001).

---

## Observações
- O backend deve estar rodando na porta **8081**.  
- Certifique-se de liberar **CORS** para permitir requisições do frontend (`http://localhost:3001`).  

---

## Licença
Projeto acadêmico desenvolvido para fins de estudo e colaboração.  

## Colaboradores
O projeto foi desenvolvido por:  
- **Marcelly Arcanjo**  
- **Carolline Barbosa**  
- **Jai Santos**  
- **Maria Clara Moutinho**  

---

