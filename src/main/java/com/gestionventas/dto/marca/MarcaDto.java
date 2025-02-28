package com.gestionventas.dto.marca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarcaDto {
    private Long id;
    private String nombre;
    private String nimagen;
    private Boolean state;
}
