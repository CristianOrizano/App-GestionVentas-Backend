package com.gestionventas.controller;

import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.ciudad.CiudadFilterDto;
import com.gestionventas.dto.ciudad.CiudadSaveDto;
import com.gestionventas.service.ICiudadService;
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

import java.util.List;
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/ciudad")
@Tag(name = "Ciudad")
public class CiudadController {

    private final ICiudadService ICiudadService;

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Ciudades")
    @GetMapping()
    public ResponseEntity<List<CiudadDto>> findAll() {
        List<CiudadDto>lista= ICiudadService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Ciudad por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "City not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<CiudadDto> findById(@PathVariable("id") Long id) {
        CiudadDto ciudad= ICiudadService.findById(id);
        return new ResponseEntity<>(ciudad, HttpStatus.OK);
    }

    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "Ciudad creado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PostMapping
    public ResponseEntity<CiudadDto> create(@Valid @RequestBody CiudadSaveDto ciudadBody) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ICiudadService.create(ciudadBody));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Ciudad actualizado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<CiudadDto> update(@PathVariable("id") Long id, @Valid @RequestBody CiudadSaveDto ciudadBody) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ICiudadService.update(id, ciudadBody));
    }

    @Operation(summary = "Obtiene una lista paginada de ciudades")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de ciudades paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<CiudadDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser numero positivo o mayor a :0")
            @RequestParam(name = "page",defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser numero positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "nombre",required = false) String nombre,

            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,

            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE,message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        CiudadFilterDto filter = CiudadFilterDto.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .nombre(nombre)
                .build();
        PageResponse<CiudadDto> response= ICiudadService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
