package com.gestionventas.dto.usuario;

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
public class UsuarioFilterDto extends PageRequest {
    private String nombre;
    private String apellido;
    private Boolean state;
}
