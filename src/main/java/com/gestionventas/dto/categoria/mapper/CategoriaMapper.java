package com.gestionventas.dto.categoria.mapper;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Ciudad;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.categoria.CategoriaSimpleListDto;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {
    CategoriaDto toDto(Categoria categoria);
    CategoriaSimpleListDto toDtoSimple(Categoria categoria);
    Categoria toEntity (CategoriaSaveDto categoriaSaveDto);
    Categoria updateEntity(CategoriaSaveDto categoriaSaveDto, @MappingTarget Categoria categoria);
}
