package com.gestionventas.service;

import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaSaveDto;

import java.util.List;
import java.util.Map;

public interface IBoletaService {
    Map<String, Object> registrarBoleta(BoletaSaveDto bean);

    List<BoletaDto> findAll();
}
