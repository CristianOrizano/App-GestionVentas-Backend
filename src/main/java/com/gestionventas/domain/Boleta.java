package com.gestionventas.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_boleta")
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    @Column(nullable = false,precision = 8, scale = 2)
    private BigDecimal total;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_cliente",insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_usuario",insertable = false, updatable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<DetalleBoleta> detalles;

}
