package com.gestionventas.repository;

import com.gestionventas.domain.Ciudad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    @Query("SELECT c FROM Ciudad c " +
            "WHERE (:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) ")
    Page<Ciudad> findAllWithFilters(
            @Param("nombre") String nombre,
            Pageable pageable
    );

}
