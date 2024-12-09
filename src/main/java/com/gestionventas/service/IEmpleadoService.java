package com.gestionventas.service;

import com.gestionventas.domain.Empleado;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadFilterDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.dto.empleado.EmpleadoDto;
import com.gestionventas.dto.empleado.EmpleadoFilterDto;
import com.gestionventas.dto.empleado.EmpleadoSaveDto;
import com.gestionventas.shared.page.PageResponse;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoDto> findAll();
    EmpleadoDto findById(Long id);
    EmpleadoDto create(EmpleadoSaveDto empleadoBody);
    EmpleadoDto update(Long id, EmpleadoSaveDto empleadoBody) ;
    EmpleadoDto disable(Long id) ;
    PageResponse<EmpleadoDto> findPaginated(EmpleadoFilterDto filter);


}
