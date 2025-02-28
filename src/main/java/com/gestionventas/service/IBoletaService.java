package com.gestionventas.service;

import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaFilterDto;
import com.gestionventas.dto.boleta.BoletaSaveDto;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.dto.producto.ProductoFilterDto;
import com.gestionventas.shared.page.PageResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IBoletaService {
    Map<String, Object> registrarBoleta(BoletaSaveDto bean);
    List<BoletaDto> findAll();
    PageResponse<BoletaDto> findPaginated(BoletaFilterDto filter);

     List<BoletaDto> obtenerBoletasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
