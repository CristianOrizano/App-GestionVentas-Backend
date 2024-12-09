package com.gestionventas.service;



import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.usuario.UsuarioDto;
import com.gestionventas.dto.usuario.UsuarioSaveDto;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioDto> findAll();
    UsuarioDto findById(Long id);
    UsuarioDto create(UsuarioSaveDto usuarioBody);
    UsuarioDto update(Long id, UsuarioSaveDto usuarioBody) ;
    UsuarioDto disable(Long id) ;
}
