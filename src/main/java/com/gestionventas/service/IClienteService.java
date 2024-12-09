package com.gestionventas.service;

import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadFilterDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.dto.cliente.ClienteDto;
import com.gestionventas.dto.cliente.ClienteFilterDto;
import com.gestionventas.dto.cliente.ClienteSaveDto;
import com.gestionventas.shared.page.PageResponse;

import java.util.List;

public interface IClienteService {
    List<ClienteDto> findAll();
    ClienteDto findById(Long id);
    ClienteDto create(ClienteSaveDto clienteBody);
    ClienteDto update(Long id, ClienteSaveDto clienteBody) ;
    ClienteDto disable(Long id) ;
    PageResponse<ClienteDto> findPaginated(ClienteFilterDto filter);
}
