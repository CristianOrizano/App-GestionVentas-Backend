package com.gestionventas.service.impl;

import com.gestionventas.domain.DetalleBoleta;
import com.gestionventas.dto.categoria.mapper.CategoriaMapper;
import com.gestionventas.dto.detalleBoleta.DetalleBoletaListDto;
import com.gestionventas.dto.detalleBoleta.mapper.DetalleBoletaMapper;
import com.gestionventas.repository.CategoriaRepository;
import com.gestionventas.repository.DetalleBoletaRepository;
import com.gestionventas.service.IDetalleService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DetalleBoletaServiceImpl implements IDetalleService {

    private final DetalleBoletaMapper detalleBoletaMapper;
    private final DetalleBoletaRepository detalleBoletaRepository;
    @Override
    public List<DetalleBoletaListDto> findAll() {
        return detalleBoletaRepository.findAll().stream()
                .map(detalleBoletaMapper::toDto)
                .toList();
    }

    @Override
    public DetalleBoletaListDto findById(Long id) {
        return detalleBoletaRepository.findById(id)
                .map(detalleBoletaMapper::toDto)
                .orElseThrow(() ->  new ResourceNotFoundException("detalleBoleta no encontrada con ID: " + id));
    }

    @Override
    public  List<DetalleBoletaListDto> findByIdBoleta(Long id) {
        return detalleBoletaRepository.findByBoletaId(id).stream()
                .map(detalleBoletaMapper::toDto)
                .toList();
    }
}
