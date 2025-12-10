package com.graodigital.grao_digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.graodigital.grao_digital.model.enums.TipoUsuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.graodigital.grao_digital.model.Usuario;
import com.graodigital.grao_digital.repository.UsuarioRepository;
import com.graodigital.grao_digital.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:3001")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Criar usuário
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setTipoUsuario(usuarioDetails.getTipoUsuario());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------------
    // Endpoint de login
    // --------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            // Converte String recebida do React no ENUM correto
            TipoUsuario tipo = TipoUsuario.valueOf(usuarioDTO.getTipoUsuario());

            // Busca no banco direto (sem stream, sem filtrar em memória)
            Optional<Usuario> usuarioOpt = usuarioRepository
                    .findByEmailAndSenhaAndTipoUsuario(
                            usuarioDTO.getEmail(),
                            usuarioDTO.getSenha(),
                            tipo
                    );

            if (usuarioOpt.isPresent()) {
                return ResponseEntity.ok(usuarioOpt.get()); // Retorna o usuário inteiro
            }

            return ResponseEntity.status(401)
                    .body("Credenciais inválidas");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Tipo de usuário inválido.");
        }
    }

}


