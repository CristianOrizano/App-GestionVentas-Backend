package com.gestionventas.repository;

import com.gestionventas.domain.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    @Query("SELECT c FROM Marca c WHERE " +
            "(:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:state IS NULL OR c.state = :state)")
    Page<Marca> findByFilters(@Param("nombre") String nombre,
                                  @Param("state") Boolean state,
                                  Pageable pageable);
}
