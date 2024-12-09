package com.gestionventas.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    private double sueldo;

    //@Temporal(TemporalType.TIMESTAMP)  // Guarda fecha y hora
    @Temporal(TemporalType.DATE)  // Solo guarda la fecha
    private Date fechanacimiento;

    @Column(name = "id_ciudad")
    private Long idCiudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_ciudad", insertable = false, updatable = false)
    private Ciudad ciudad;

    private boolean state;
}
