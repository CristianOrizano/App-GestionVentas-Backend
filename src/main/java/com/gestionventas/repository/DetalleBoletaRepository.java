package com.gestionventas.repository;

import com.gestionventas.domain.DetalleBoleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Long> {
    // Buscar todos los detalles de una boleta por id_boleta
    List<DetalleBoleta> findByBoletaId(Long idBoleta);
}
