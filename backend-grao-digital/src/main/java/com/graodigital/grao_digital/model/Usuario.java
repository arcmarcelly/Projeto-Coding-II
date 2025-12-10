package com.graodigital.grao_digital.model;

import jakarta.persistence.*;
import com.graodigital.grao_digital.model.enums.TipoUsuario;

	@Entity
	@Table(name = "Usuario")
	public class Usuario {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idUsuario")
	    private Integer idUsuario;

	    @Column(name = "nome", nullable = false)
	    private String nome;

	    @Column(name = "email", nullable = false, unique = true)
	    private String email;

	    // não salve senhas em texto; depois usaremos bcrypt
	    @Column(name = "senha", nullable = false)
	    private String senha;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "tipo_usuario")
	    private TipoUsuario tipoUsuario;

	    // construtores
	    public Usuario() {}

	    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
	        this.nome = nome;
	        this.email = email;
	        this.senha = senha;
	        this.tipoUsuario = tipoUsuario;
	    }

	    // getters / setters
	    public Integer getIdUsuario() { return idUsuario; }
	    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

	    public String getNome() { return nome; }
	    public void setNome(String nome) { this.nome = nome; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public String getSenha() { return senha; }
	    public void setSenha(String senha) { this.senha = senha; }

	    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
	    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
	}

