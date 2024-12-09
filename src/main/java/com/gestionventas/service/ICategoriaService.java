package com.gestionventas.service;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaFilterDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadFilterDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.shared.page.PageResponse;

import java.util.List;

public interface ICategoriaService {
    List<CategoriaDto> findAll();
    CategoriaDto findById(Long id);
    CategoriaDto create(CategoriaSaveDto categoriaBody);
    CategoriaDto update(Long id, CategoriaSaveDto categoriaBody) ;
    CategoriaDto disable(Long id) ;
    PageResponse<CategoriaDto> findPaginated(CategoriaFilterDto filter);
}
