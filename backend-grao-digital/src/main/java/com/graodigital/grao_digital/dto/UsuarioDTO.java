package com.graodigital.grao_digital.dto;

public class UsuarioDTO {
    private String email;
    private String senha;
    private String tipoUsuario; // importante para login com enum

    // email
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // senha
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    // tipoUsuario
    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}
