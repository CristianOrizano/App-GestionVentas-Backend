package com.gestionventas.dto.boleta.mapper;

import com.gestionventas.domain.Boleta;
import com.gestionventas.domain.Categoria;
import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaSaveDto;
import com.gestionventas.dto.categoria.CategoriaDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BoletaMapper {
    BoletaDto toDto(Boleta boleta);

    Boleta toEntity(BoletaSaveDto save);
}
