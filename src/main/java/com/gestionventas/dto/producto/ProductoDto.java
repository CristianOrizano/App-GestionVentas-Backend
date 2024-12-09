package com.gestionventas.dto.producto;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaSimpleListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
    private Long id;
    private String descripcion;
    private Integer stock;
    private Double precio;
    private String marca;
    private String nimagen;
    private CategoriaSimpleListDto categoria;
    private Boolean state;
}