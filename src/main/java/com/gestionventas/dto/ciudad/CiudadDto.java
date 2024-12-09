package com.gestionventas.dto.ciudad;

import com.gestionventas.dto.empleado.EmpleadoDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CiudadDto {
    private Long id;
    private String cityName;

}
