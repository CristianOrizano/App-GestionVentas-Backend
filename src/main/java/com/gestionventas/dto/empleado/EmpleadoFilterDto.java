package com.gestionventas.dto.empleado;

import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.shared.page.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoFilterDto extends PageRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private Integer telefono;  // Usamos Integer para poder manejar valores nulos
    private Double sueldo;     // Usamos Double para permitir valores nulos
    private Date fechanacimiento;
    private Boolean state;     // Usamos Boolean para manejar valores nulos

}
