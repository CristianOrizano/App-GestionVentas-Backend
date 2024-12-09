package com.gestionventas.dto.usuario.mapper;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Usuario;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.usuario.UsuarioDto;
import com.gestionventas.dto.usuario.UsuarioSaveDto;
import com.gestionventas.dto.usuario.UsuarioSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    UsuarioDto toDto(Usuario usuario);
    UsuarioSimpleDto toSimpleDto(Usuario usuario);

    @Mapping(target = "roles", ignore = true) // Ignorar roles, serán manejados en el servicio
    Usuario toEntity (UsuarioSaveDto usuarioSaveDto);
}
