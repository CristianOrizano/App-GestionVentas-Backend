package com.gestionventas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String nombre;
    private String descripcion;
    private Boolean state;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria",cascade = CascadeType.ALL)
    private List<Producto> listaCategorias;
}
