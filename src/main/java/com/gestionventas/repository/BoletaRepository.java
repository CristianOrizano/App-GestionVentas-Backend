package com.gestionventas.repository;

import com.gestionventas.domain.Boleta;
import com.gestionventas.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BoletaRepository extends JpaRepository<Boleta, Long> {

    @Query("SELECT b FROM Boleta b WHERE b.fechaEmision BETWEEN :fechaInicio AND :fechaFin")
    List<Boleta> findByFechaEmisionBetween(@Param("fechaInicio") LocalDateTime fechaInicio,
                                           @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT b FROM Boleta b WHERE " +
            "(:fechaInicio IS NULL OR b.fechaEmision >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR b.fechaEmision <= :fechaFin) AND " +
            "(:tipoVenta IS NULL OR b.tipoVenta = :tipoVenta) AND " +
            "(:idUsuario IS NULL OR b.idUsuario = :idUsuario)")
    Page<Boleta> findByFilters(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("tipoVenta") String tipoVenta,
            @Param("idUsuario") Long idUsuario,
            Pageable pageable);

}
