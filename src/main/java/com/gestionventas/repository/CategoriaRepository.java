package com.gestionventas.repository;

import com.gestionventas.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c WHERE " +
            "(:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:descripcion IS NULL OR LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))) AND " +
            "(:state IS NULL OR c.state = :state)")
    Page<Categoria> findByFilters(@Param("nombre") String nombre,
                                  @Param("descripcion") String descripcion,
                                  @Param("state") Boolean state,
                                  Pageable pageable);
}
