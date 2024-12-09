package com.gestionventas.dto.categoria;

import com.gestionventas.shared.page.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaFilterDto extends PageRequest {
    private String nombre;
    private String descripcion;
    private Boolean state;
}
