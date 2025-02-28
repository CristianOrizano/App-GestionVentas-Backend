package com.gestionventas.dto.notificacion.mapper;

import com.gestionventas.domain.Cliente;
import com.gestionventas.domain.Notificacion;
import com.gestionventas.dto.cliente.ClienteDto;
import com.gestionventas.dto.cliente.ClienteSaveDto;
import com.gestionventas.dto.notificacion.NotificacionDto;
import com.gestionventas.dto.notificacion.NotificacionSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotifiacacionMapper {
    NotificacionDto toDto(Notificacion notificacion);
    Notificacion toEntity (NotificacionSaveDto notificacionSaveDto);

}
