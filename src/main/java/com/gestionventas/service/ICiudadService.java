package com.gestionventas.service;

import com.gestionventas.domain.Ciudad;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadFilterDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.shared.page.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICiudadService {
    List<CiudadDto> findAll();
    CiudadDto findById(Long id);
    CiudadDto create(CiudadSaveDto ciudadBody);
    CiudadDto update(Long id, CiudadSaveDto ciudadBody) ;
    CiudadDto disable(Long id) ;
    PageResponse<CiudadDto> findPaginated(CiudadFilterDto filter);
}
