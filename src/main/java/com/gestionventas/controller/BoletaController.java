package com.gestionventas.controller;

import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaFilterDto;
import com.gestionventas.dto.boleta.BoletaSaveDto;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaFilterDto;
import com.gestionventas.service.IBoletaService;
import com.gestionventas.service.ICategoriaService;
import com.gestionventas.shared.constant.HttpStatusCodes;
import com.gestionventas.shared.page.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    @GetMapping("/filtrar")
    public ResponseEntity<List<BoletaDto>> obtenerBoletasPorRangoFechas(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate  fechaFin
    ) {
        // Convertimos LocalDate a LocalDateTime para incluir toda la fecha sin afectar el rango
        LocalDateTime fechaInicioDateTime = fechaInicio.atStartOfDay(); // 00:00:00
        LocalDateTime fechaFinDateTime = fechaFin.atTime(23, 59, 59); // 23:59:59

        List<BoletaDto> boletas = IboletaService.obtenerBoletasPorRangoFechas(fechaInicioDateTime, fechaFinDateTime);
        return ResponseEntity.ok(boletas);
    }

    @GetMapping()
    public ResponseEntity<List<BoletaDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IboletaService.findAll());
    }

    @Operation(summary = "Obtiene una lista paginada de boleta")
    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Lista de boleta paginada")
    @GetMapping("paginated")
    public ResponseEntity<PageResponse<BoletaDto>> findAllPaginated(
            @Min(value = 1, message = "Page debe ser número positivo o mayor a 0")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @Min(value = 1, message = "Size debe ser número positivo")
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El valor de 'sortDir' debe ser 'asc' o 'desc'")
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        // Crear el filtro manualmente
        BoletaFilterDto filter = BoletaFilterDto.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortDir)
                .build();
        PageResponse<BoletaDto> response = IboletaService.findPaginated(filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
