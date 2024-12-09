package com.gestionventas.dto.producto;

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
public class ProductoFilterDto extends PageRequest {
    private String descripcion;
    private Integer stock;
    private Double precio;
    private String marca;
    private String nimagen;
    private Long idCategoria;
    private Boolean state;
}
