package com.gestionventas.controller;

import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaSaveDto;
import com.gestionventas.service.IBoletaService;
import com.gestionventas.service.ICategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/boleta")
@Tag(name = "Boleta")
public class BoletaController {
    private final IBoletaService IboletaService;

    @PostMapping("/guardar")
    public ResponseEntity<Map<String, Object>> guardarBoleta(@RequestBody BoletaSaveDto boletaSaveDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IboletaService.registrarBoleta(boletaSaveDto));
    }

}
