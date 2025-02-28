package com.gestionventas.dto.marca.mapper;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Marca;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.categoria.CategoriaSimpleListDto;
import com.gestionventas.dto.marca.MarcaDto;
import com.gestionventas.dto.marca.MarcaSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarcaMapper {
    MarcaDto toDto(Marca marca);
    Marca toEntity (MarcaSaveDto marcaSaveDto);
    Marca updateEntity(MarcaSaveDto marcaSaveDto, @MappingTarget Marca marca);

}
