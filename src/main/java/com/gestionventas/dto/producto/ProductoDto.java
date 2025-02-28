package com.gestionventas.dto.producto;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaSimpleListDto;
import com.gestionventas.dto.marca.MarcaDto;
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
    private Integer descuento;
    private String nimagen;
    private CategoriaSimpleListDto categoria;
    private MarcaDto marca;
    private Boolean state;
}