package com.gestionventas.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaSaveDto {
    @Size(min = 3, message = "El nombre de la categoría debe tener al menos 3 caracteres")
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;

    @Size(min = 5, message = "La descripción debe tener al menos 5 caracteres")
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}
