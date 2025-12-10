package com.graodigital.grao_digital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Agricultor")
public class Agricultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgricultor;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 20)
    private String telefone;

    @Column(length = 200)
    private String localEndereco;

    @Column(unique = true, length = 14)
    private String CPF;

    @Column(length = 100)
    private String tipoCultura;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario; // relacionamento com a tabela Usuario

    // Getters e Setters
    public Integer getIdAgricultor() { return idAgricultor; }
    public void setIdAgricultor(Integer idAgricultor) { this.idAgricultor = idAgricultor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getLocalEndereco() { return localEndereco; }
    public void setLocalEndereco(String localEndereco) { this.localEndereco = localEndereco; }

    public String getCPF() { return CPF; }
    public void setCPF(String CPF) { this.CPF = CPF; }

    public String getTipoCultura() { return tipoCultura; }
    public void setTipoCultura(String tipoCultura) { this.tipoCultura = tipoCultura; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
