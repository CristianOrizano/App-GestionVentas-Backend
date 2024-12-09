package com.gestionventas.repository;

import com.gestionventas.domain.Boleta;
import com.gestionventas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletaRepository extends JpaRepository<Boleta, Long> {
}
