package com.gestionventas.controller;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.detalleBoleta.DetalleBoletaListDto;
import com.gestionventas.service.ICategoriaService;
import com.gestionventas.service.IDetalleService;
import com.gestionventas.shared.constant.HttpStatusCodes;
import com.gestionventas.shared.exeption.ErrorDetalles;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/detalleboleta")
@Tag(name = "DetalleBoleta")
public class DetalleBoletaController {

    private final IDetalleService detalleBoletaService;
    @GetMapping()
    public ResponseEntity<List<DetalleBoletaListDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(detalleBoletaService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "DetalleBoleta por id")
    @ApiResponse(
            responseCode = HttpStatusCodes.NOT_FOUND,
            description = "DetalleBoleta not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetalles.class)
            )
    )
    @GetMapping("/buscarporidboleta/{id}")
    public ResponseEntity<List<DetalleBoletaListDto>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(detalleBoletaService.findByIdBoleta(id));
    }

}
