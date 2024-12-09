package com.gestionventas.dto.empleado.mapper;


import com.gestionventas.domain.Ciudad;
import com.gestionventas.domain.Empleado;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.dto.ciudad.mapper.CiudadMapper;
import com.gestionventas.dto.empleado.EmpleadoDto;
import com.gestionventas.dto.empleado.EmpleadoSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = CiudadMapper.class)
public interface EmpleadoMapper {
    @Mapping(source = "nombre", target = "nombreEm")
    EmpleadoDto toEmpleadoDto(Empleado empleado);
    Empleado toEmpleado (EmpleadoSaveDto empleadoSaveDto);
    Empleado updateEmpleado(EmpleadoSaveDto empleadoSaveDto, @MappingTarget Empleado ciudad);
}
