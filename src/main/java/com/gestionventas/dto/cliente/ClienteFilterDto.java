package com.gestionventas.dto.cliente;

import com.gestionventas.shared.page.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteFilterDto  extends PageRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private Integer ndocumento;
    private Integer telefono;
    private Boolean state;

}
