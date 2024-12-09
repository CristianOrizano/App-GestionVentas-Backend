package com.gestionventas.dto.cliente.mapper;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Cliente;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.dto.cliente.ClienteDto;
import com.gestionventas.dto.cliente.ClienteSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    ClienteDto toDto(Cliente cliente);
    Cliente toEntity (ClienteSaveDto clienteSaveDto);
    Cliente updateEntity(ClienteSaveDto clienteSaveDto, @MappingTarget Cliente cliente);
}
