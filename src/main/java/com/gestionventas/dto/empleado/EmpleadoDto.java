package com.gestionventas.dto.empleado;

import com.gestionventas.dto.ciudad.CiudadDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {
    private Long id;
    private String nombreEm;
    private String apellido;
    private String direccion;
    private int telefono;
    private double sueldo;
    private Date fechanacimiento;
    private CiudadDto ciudad;
    private boolean state;
}
