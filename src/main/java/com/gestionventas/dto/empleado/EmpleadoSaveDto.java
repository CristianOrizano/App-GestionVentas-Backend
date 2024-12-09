package com.gestionventas.dto.empleado;

import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoSaveDto {
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres.")
    private String apellido;

    @NotBlank(message = "La dirección no puede estar vacía.")
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres.")
    private String direccion;

    @NotNull(message = "El teléfono es obligatorio.")
    @Min(value = 100000000, message = "El teléfono debe tener al menos 9 dígitos.")
    @Max(value = 999999999, message = "El teléfono no puede tener más de 9 dígitos.")
    private Integer telefono;

    @NotNull(message = "El sueldo es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El sueldo debe ser mayor que 0.")
    private Double sueldo;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada.")
    private Date fechanacimiento;

    @NotNull(message = "El ID de la ciudad es obligatorio.")
    @Positive(message = "El ID de la ciudad debe ser un número positivo.")
    private Long idCiudad;
}
