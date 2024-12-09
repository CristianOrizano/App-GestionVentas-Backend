package com.gestionventas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_ciudad")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 100, nullable = true)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "ciudad",cascade = CascadeType.ALL)
    private List<Empleado> listaEmpleados;
}
