package com.gestionventas.repository;

import com.gestionventas.domain.Ciudad;
import com.gestionventas.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository   extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE " +
            "(:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:apellido IS NULL OR LOWER(c.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))) AND " +
            "(:direccion IS NULL OR LOWER(c.direccion) LIKE LOWER(CONCAT('%', :direccion, '%'))) AND " +
            "(:ndocumento IS NULL OR c.ndocumento = :ndocumento) AND " +
            "(:telefono IS NULL OR c.telefono = :telefono) AND " +
            "(:state IS NULL OR c.state = :state)")
    Page<Cliente> findByFilters(@Param("nombre") String nombre,
                                @Param("apellido") String apellido,
                                @Param("direccion") String direccion,
                                @Param("ndocumento") Integer ndocumento,
                                @Param("telefono") Integer telefono,
                                @Param("state") Boolean state,
                                Pageable pageable);
}
