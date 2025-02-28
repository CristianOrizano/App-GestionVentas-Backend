package com.gestionventas.service;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaFilterDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.marca.MarcaDto;
import com.gestionventas.dto.marca.MarcaFilterDto;
import com.gestionventas.dto.marca.MarcaSaveDto;
import com.gestionventas.shared.page.PageResponse;

import java.util.List;

public interface IMarcaService {
    List<MarcaDto> findAll();
    MarcaDto findById(Long id);
    MarcaDto create(MarcaSaveDto marcaBody);
    MarcaDto update(Long id, MarcaSaveDto marcaBody) ;
    MarcaDto disable(Long id) ;
    PageResponse<MarcaDto> findPaginated(MarcaFilterDto filter);

}
