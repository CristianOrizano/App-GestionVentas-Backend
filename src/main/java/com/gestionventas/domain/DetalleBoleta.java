package com.gestionventas.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_detalle_boleta", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_boleta", "id_producto"})
})
public class DetalleBoleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_producto")
    private Long idProducto;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_boleta", nullable = false)
    private Boleta boleta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false,insertable = false, updatable = false)
    private Producto producto;
}
