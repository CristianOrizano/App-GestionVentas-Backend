package com.gestionventas.service.impl;

import com.gestionventas.domain.Notificacion;
import com.gestionventas.dto.notificacion.NotificacionDto;
import com.gestionventas.dto.notificacion.NotificacionSaveDto;
import com.gestionventas.dto.notificacion.mapper.NotifiacacionMapper;
import com.gestionventas.repository.NotificacionRepository;
import com.gestionventas.service.INotificacionService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificacionServiceImpl implements INotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final NotifiacacionMapper notificacionMapper;
    @Override
    public List<NotificacionDto> findAll() {
        return notificacionRepository.findAll().stream()
                .filter(notificacion -> Boolean.TRUE.equals(notificacion.getState()))
                .map(notificacionMapper::toDto)
                .toList();
    }

    @Override
    public NotificacionDto create(NotificacionSaveDto notificacionBody) {
        Notificacion notificacion = notificacionMapper.toEntity(notificacionBody);
        notificacion.setState(true);
        return notificacionMapper.toDto(notificacionRepository.save(notificacion));
    }

    @Override
    public void notificacion(String message,Long idProducto) {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(message);
        notificacion.setState(true);
        notificacion.setFecha(new Date());
        notificacion.setIdProducto(idProducto);
        notificacionRepository.save(notificacion);
    }

    @Override
    public NotificacionDto disable(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Notificacion no encontrada con ID: " + id));
        notificacion.setState(false);
        return notificacionMapper.toDto(notificacionRepository.save(notificacion));
    }
}
