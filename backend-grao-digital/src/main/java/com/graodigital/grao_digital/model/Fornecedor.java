package com.graodigital.grao_digital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFornecedor")
    private Integer idFornecedor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "contato")
    private String contato;

    public Fornecedor() {}

    // getters/setters
    public Integer getIdFornecedor() { return idFornecedor; }
    public void setIdFornecedor(Integer idFornecedor) { this.idFornecedor = idFornecedor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
}
