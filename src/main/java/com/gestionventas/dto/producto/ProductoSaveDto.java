package com.gestionventas.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSaveDto {
    private String descripcion;
    private Integer stock;
    private Double precio;
    private String marca;
    private String nimagen;
    private Long idCategoria;

}
