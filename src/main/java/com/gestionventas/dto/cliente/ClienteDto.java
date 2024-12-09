package com.gestionventas.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private Integer ndocumento;
    private Integer telefono;
    private Boolean state;
}