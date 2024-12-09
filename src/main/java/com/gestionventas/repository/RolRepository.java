package com.gestionventas.repository;

import com.gestionventas.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository  extends JpaRepository<Rol, Long> {
}
