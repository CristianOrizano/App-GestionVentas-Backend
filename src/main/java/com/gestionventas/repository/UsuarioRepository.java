package com.gestionventas.repository;

import com.gestionventas.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    @Query("SELECT u FROM Usuario u WHERE " +
            "(:nombre IS NULL OR LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:apellido IS NULL OR LOWER(u.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))) AND " +
            "(:state IS NULL OR u.state = :state)")
    Page<Usuario> findByFilters(@Param("nombre") String nombre,
                                @Param("apellido") String apellido,
                                @Param("state") Boolean state,
                                Pageable pageable);
}
