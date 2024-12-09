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
    private String marca;
    private String nimagen;

    @Column(name = "id_categoria")
    private Long idCategoria;

    private Boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categoria",insertable = false, updatable = false)
    private Categoria categoria;
}
