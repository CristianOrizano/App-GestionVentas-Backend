package com.gestionventas.controller;

import com.gestionventas.dto.marca.MarcaDto;
import com.gestionventas.dto.marca.MarcaFilterDto;
import com.gestionventas.dto.marca.MarcaSaveDto;
import com.gestionventas.service.IMarcaService;
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
@RequestMapping("/api/marca")
@Tag(name = "Marca")
public class MarcaController {

    private final IMarcaService marcaService;

    @GetMapping()
    public ResponseEntity<List<MarcaDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(marcaService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Marca por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Marca not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(marcaService.findById(id));
    }

    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "Categoria creado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PostMapping
    public ResponseEntity<MarcaDto> create(@Valid @RequestBody MarcaSaveDto marcaBody) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(marcaService.create(marcaBody));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Marca actualizado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<MarcaDto> update(@PathVariable("id") Long id, @Valid @RequestBody MarcaSaveDto marcaBody) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(marcaService.update(id, marcaBody));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Marca Desabilitar")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Marca not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<MarcaDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(marcaService.disable(id));
    }

    @Operation(summary = "Obtiene una lista paginada de Marcas")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de marcas paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<MarcaDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser número positivo o mayor a 0")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser número positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "state", required = false) Boolean state,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,

            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        MarcaFilterDto filter = MarcaFilterDto.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .nombre(nombre)
                .state(state)
                .build();
        PageResponse<MarcaDto> response = marcaService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






}
