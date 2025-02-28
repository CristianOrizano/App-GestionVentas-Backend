package com.gestionventas.controller;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.ciudad.CiudadDto;
import com.gestionventas.dto.notificacion.NotificacionDto;
import com.gestionventas.service.ICiudadService;
import com.gestionventas.service.INotificacionService;
import com.gestionventas.shared.constant.HttpStatusCodes;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/notificacion")
@Tag(name = "Notificacion")
public class NotificacionController {

    private final INotificacionService notificacionService;

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Notificaciones")
    @GetMapping()
    public ResponseEntity<List<NotificacionDto>> findAll() {
        List<NotificacionDto>lista= notificacionService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NotificacionDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(notificacionService.disable(id));
    }


}
