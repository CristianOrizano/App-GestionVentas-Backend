package com.gestionventas.dto.detalleBoleta.mapper;

import com.gestionventas.domain.DetalleBoleta;
import com.gestionventas.dto.detalleBoleta.DetalleBoletaListDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DetalleBoletaMapper {
    DetalleBoletaListDto toDto(DetalleBoleta detalleBoleta);

}
