package com.gestionventas.controller;

import com.gestionventas.dto.usuario.UsuarioDto;
import com.gestionventas.dto.usuario.UsuarioSaveDto;
import com.gestionventas.service.IUsuarioService;
import com.gestionventas.shared.constant.HttpStatusCodes;
import com.gestionventas.shared.exeption.ErrorDetalles;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.disable(id));
    }


}
