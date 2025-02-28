package com.gestionventas.repository;

import com.gestionventas.domain.Ciudad;
import com.gestionventas.domain.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

}
