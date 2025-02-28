package com.gestionventas.dto.detalleBoleta;

import com.gestionventas.domain.Boleta;
import com.gestionventas.domain.Producto;
import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.producto.ProductoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleBoletaListDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;
    private BoletaDto boleta;
    private ProductoDto producto;

}
