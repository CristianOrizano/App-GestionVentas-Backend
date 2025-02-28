package com.gestionventas.service;
import com.gestionventas.dto.notificacion.NotificacionDto;
import com.gestionventas.dto.notificacion.NotificacionSaveDto;

import java.util.List;

public interface INotificacionService {
    List<NotificacionDto> findAll();
    NotificacionDto create(NotificacionSaveDto notificacionBody);
    void notificacion(String message,Long idProducto);
    NotificacionDto disable(Long id) ;
}
