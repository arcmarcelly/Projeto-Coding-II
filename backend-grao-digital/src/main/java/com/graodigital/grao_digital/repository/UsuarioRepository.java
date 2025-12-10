package com.graodigital.grao_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.graodigital.grao_digital.model.Usuario;
import com.graodigital.grao_digital.model.enums.TipoUsuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailAndSenhaAndTipoUsuario(
            String email,
            String senha,
            TipoUsuario tipoUsuario
    );
}
