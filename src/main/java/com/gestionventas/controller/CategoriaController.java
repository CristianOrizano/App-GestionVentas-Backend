package com.gestionventas.controller;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaFilterDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.service.ICategoriaService;
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
@RequestMapping("/api/categoria")
@Tag(name = "Categoria")
public class CategoriaController {

    private final ICategoriaService ICategoriaService;

    @GetMapping()
    public ResponseEntity<List<CategoriaDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ICategoriaService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Categoria por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Categoria not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ICategoriaService.findById(id));
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
    public ResponseEntity<CategoriaDto> create(@Valid @RequestBody CategoriaSaveDto categoriaBody) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ICategoriaService.create(categoriaBody));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Categoria actualizado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable("id") Long id, @Valid @RequestBody CategoriaSaveDto categoriaBody) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ICategoriaService.update(id, categoriaBody));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Categoria Desabilitar")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Categoria not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ICategoriaService.disable(id));
    }

    @Operation(summary = "Obtiene una lista paginada de categorías")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de categorías paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<CategoriaDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser número positivo o mayor a 0")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser número positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "state", required = false) Boolean state,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,

            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        CategoriaFilterDto filter = CategoriaFilterDto.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .nombre(nombre)
                .descripcion(descripcion)
                .state(state)
                .build();
        PageResponse<CategoriaDto> response = ICategoriaService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
