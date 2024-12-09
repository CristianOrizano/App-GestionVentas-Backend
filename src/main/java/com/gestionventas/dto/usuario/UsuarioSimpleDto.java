package com.gestionventas.dto.usuario;

import com.gestionventas.dto.role.RolDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSimpleDto {
    private Long id;
    private String nombre;
    private String apellido;

}
