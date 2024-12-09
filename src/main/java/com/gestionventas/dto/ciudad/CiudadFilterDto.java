package com.gestionventas.dto.ciudad;

import com.gestionventas.shared.page.PageRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadFilterDto extends PageRequest {
    private String nombre;
}

/* SuperBuilder Es especialmente útil para clases que participan en una jerarquía de herencia.
 Permite acceder a los atributos de las clases padres y los métodos del builder para esas clases.*/