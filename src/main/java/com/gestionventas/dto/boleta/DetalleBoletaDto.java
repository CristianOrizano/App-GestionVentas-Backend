package com.gestionventas.dto.boleta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleBoletaDto {
    private Long idProducto;
    private Integer cantidad;
}
