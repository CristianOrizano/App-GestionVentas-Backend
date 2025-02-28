package com.gestionventas.dto.notificacion;

import com.gestionventas.dto.producto.ProductoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDto {
    private Long id;
    private String mensaje;
    private Boolean state;
    private Date fecha;
    private ProductoDto producto;
}
