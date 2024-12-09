package com.gestionventas.dto.ciudad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CiudadSaveDto {
    @Size(min = 3,message = "el nombre minimo 3 caracteres")
    @NotBlank(message = "El nombre obligatorio")
    private String nombre;
}
