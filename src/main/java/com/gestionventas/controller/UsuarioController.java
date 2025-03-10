package com.gestionventas.controller;

import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaFilterDto;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.usuario.UsuarioDto;
import com.gestionventas.dto.usuario.UsuarioFilterDto;
import com.gestionventas.dto.usuario.UsuarioSaveDto;
import com.gestionventas.service.IUsuarioService;
import com.gestionventas.shared.constant.HttpStatusCodes;
import com.gestionventas.shared.exeption.ErrorDetalles;
import com.gestionventas.shared.page.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
@RequestMapping("/api/usuario")
@Tag(name = "Usuario")
public class UsuarioController {

    private final IUsuarioService IUsuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Usuario por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Usuario not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.findById(id));
    }

    @SecurityRequirements(value = {})
    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "User created")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PostMapping
    public ResponseEntity<UsuarioDto> create(@Valid @RequestBody UsuarioSaveDto userCreate) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IUsuarioService.create(userCreate));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Desabilitar Usuario")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "Usuario not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Usuario actualizado")
    @ApiResponse(
            responseCode = HttpStatusCodes.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable("id") Long id, @Valid @RequestBody UsuarioSaveDto usuarioBody) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.update(id, usuarioBody));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.disable(id));
    }

    @Operation(summary = "Obtiene una lista paginada de usuario")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de usuarios paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<UsuarioDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser número positivo o mayor a 0")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser número positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "state", required = false) Boolean state,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        UsuarioFilterDto filter = UsuarioFilterDto.builder()
                .page(page)
                .size(size)
                .apellido(apellido)
                .nombre(nombre)
                .state(state)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .build();
        PageResponse<UsuarioDto> response = IUsuarioService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
