package com.gestionventas.controller;


import com.gestionventas.dto.empleado.EmpleadoDto;
import com.gestionventas.dto.empleado.EmpleadoFilterDto;
import com.gestionventas.dto.empleado.EmpleadoSaveDto;
import com.gestionventas.service.IEmpleadoService;
import com.gestionventas.shared.constant.HttpStatusCodes;
import com.gestionventas.shared.exeption.ErrorDetalles;
import com.gestionventas.shared.page.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/empleado")
@Tag(name = "Empleado")
public class EmpleadoController {

    private final IEmpleadoService IEmpleadoService;

    @GetMapping()
    public ResponseEntity<List<EmpleadoDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IEmpleadoService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Empleadp por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Empleado not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IEmpleadoService.findById(id));
    }

    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "Empleadp creado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PostMapping
    public ResponseEntity<EmpleadoDto> create(@Valid @RequestBody EmpleadoSaveDto empleadoBody) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IEmpleadoService.create(empleadoBody));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Empleado actualizado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> update(@PathVariable("id") Long id, @Valid @RequestBody EmpleadoSaveDto empleadoBody) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IEmpleadoService.update(id, empleadoBody));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Empleado Desabilitar")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Empleado not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<EmpleadoDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IEmpleadoService.disable(id));
    }

    @Operation(summary = "Obtiene una lista paginada de empleados")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de empleados paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<EmpleadoDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser número positivo o mayor a 0")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser número positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "telefono", required = false) Integer telefono,
            @RequestParam(value = "sueldo", required = false) Double sueldo,
            @RequestParam(value = "fechanacimiento", required = false) Date fechanacimiento,
            @RequestParam(value = "state", required = false) Boolean state,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,

            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        EmpleadoFilterDto filter = EmpleadoFilterDto.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .nombre(nombre)
                .apellido(apellido)
                .direccion(direccion)
                .telefono(telefono)
                .sueldo(sueldo)
                .fechanacimiento(fechanacimiento)
                .state(state)
                .build();
        PageResponse<EmpleadoDto> response = IEmpleadoService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
