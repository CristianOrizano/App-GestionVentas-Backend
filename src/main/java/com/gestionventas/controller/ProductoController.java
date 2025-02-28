package com.gestionventas.controller;

import com.gestionventas.dto.empleado.EmpleadoDto;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.dto.producto.ProductoFilterDto;
import com.gestionventas.dto.producto.ProductoSaveDto;
import com.gestionventas.service.IProductoService;
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
@RequestMapping("/api/producto")
@Tag(name = "Producto")
public class ProductoController {

    private final IProductoService IProductoService;

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Productos")
    @GetMapping()
    public ResponseEntity<List<ProductoDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IProductoService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Producto por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Producto not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IProductoService.findById(id));
    }

    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "Producto creado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PostMapping
    public ResponseEntity<ProductoDto> create(@Valid @RequestBody ProductoSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IProductoService.create(saveDto));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Producto actualizado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update(@PathVariable("id") Long id, @Valid @RequestBody ProductoSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IProductoService.update(id, saveDto));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Producto Desabilitar")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Producto not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IProductoService.disable(id));
    }

    @Operation(summary = "Obtiene una lista paginada de productos")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de productos paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<ProductoDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser un número positivo o mayor a 0")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser un número positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam(value = "precio", required = false) Double precio,
            @RequestParam(value = "idMarca", required = false) Long idMarca,
            @RequestParam(value = "idCategoria", required = false) Long idCategoria,
            @RequestParam(value = "state", required = false) Boolean state,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,

            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        ProductoFilterDto filter = ProductoFilterDto.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .descripcion(descripcion)
                .stock(stock)
                .precio(precio)
                .idMarca(idMarca)
                .idCategoria(idCategoria)
                .state(state)
                .build();
        PageResponse<ProductoDto> response = IProductoService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
