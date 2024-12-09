package com.gestionventas.controller;

import com.gestionventas.dto.cliente.ClienteDto;
import com.gestionventas.dto.cliente.ClienteFilterDto;
import com.gestionventas.dto.cliente.ClienteSaveDto;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.service.IClienteService;
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
@RequestMapping("/api/cliente")
@Tag(name = "Cliente")
public class ClienteController {

    private final IClienteService IClienteService;

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Clientes")
    @GetMapping()
    public ResponseEntity<List<ClienteDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IClienteService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Cliente por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Cliente not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IClienteService.findById(id));
    }

    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "cliente creado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IClienteService.create(saveDto));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Cliente actualizado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable("id") Long id, @Valid @RequestBody ClienteSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IClienteService.update(id, saveDto));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Cliente Desabilitar")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Cliente not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IClienteService.disable(id));
    }

    @Operation(summary = "Obtiene una lista paginada de clientes")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de clientes paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<ClienteDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser un número positivo o mayor a 0")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser un número positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "ndocumento", required = false) Integer ndocumento,
            @RequestParam(value = "telefono", required = false) Integer telefono,
            @RequestParam(value = "state", required = false) Boolean state,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,

            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        ClienteFilterDto filter = ClienteFilterDto.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .nombre(nombre)
                .apellido(apellido)
                .direccion(direccion)
                .ndocumento(ndocumento)
                .telefono(telefono)
                .state(state)
                .build();
        PageResponse<ClienteDto> response = IClienteService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
