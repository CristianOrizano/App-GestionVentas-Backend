package com.gestionventas.service;

import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.detalleBoleta.DetalleBoletaListDto;

import java.util.List;

public interface IDetalleService {
    List<DetalleBoletaListDto> findAll();
    DetalleBoletaListDto findById(Long id);
    List<DetalleBoletaListDto> findByIdBoleta(Long id);
}
