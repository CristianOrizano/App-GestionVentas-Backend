package com.gestionventas.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Integer stock;
    private Double precio;
    private Integer descuento;
    private String nimagen;

    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "id_marca")
    private Long idMarca;

    private Boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categoria",insertable = false, updatable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_marca",insertable = false, updatable = false)
    private Marca marca;
}
