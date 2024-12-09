package com.gestionventas.repository;

import com.gestionventas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);

}
