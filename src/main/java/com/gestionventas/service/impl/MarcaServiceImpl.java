package com.gestionventas.service.impl;
import com.gestionventas.domain.Marca;
import com.gestionventas.dto.marca.MarcaDto;
import com.gestionventas.dto.marca.MarcaFilterDto;
import com.gestionventas.dto.marca.MarcaSaveDto;
import com.gestionventas.dto.marca.mapper.MarcaMapper;
import com.gestionventas.repository.MarcaRepository;
import com.gestionventas.service.IMarcaService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import com.gestionventas.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MarcaServiceImpl implements IMarcaService {

    private final MarcaMapper marcaMapper;
    private final MarcaRepository marcaRepository;

    @Override
    public List<MarcaDto> findAll() {
        return marcaRepository.findAll().stream()
                .filter(marca -> Boolean.TRUE.equals(marca.getState())) // Filtra categorías activas
                .map(marcaMapper::toDto)                                   // Mapea a DTOs
                .toList();
    }

    @Override
    public MarcaDto findById(Long id) {
        return marcaRepository.findById(id)
                .map(marcaMapper::toDto)
                .orElseThrow(() ->  new ResourceNotFoundException("Marca no encontrada con ID: " + id));
    }

    @Override
    public MarcaDto create(MarcaSaveDto marcaBody) {
        Marca marca = marcaMapper.toEntity(marcaBody);
        marca.setState(true);
        return marcaMapper.toDto(marcaRepository.save(marca));
    }

    @Override
    public MarcaDto update(Long id, MarcaSaveDto marcaBody) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Marca no encontrada con ID: " + id));
        marcaMapper.updateEntity(marcaBody,marca);
        return marcaMapper.toDto(marcaRepository.save(marca));
    }

    @Override
    public MarcaDto disable(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Marca no encontrada con ID: " + id));
        marca.setState(!marca.getState());
        return marcaMapper.toDto(marcaRepository.save(marca));
    }

    @Override
    public PageResponse<MarcaDto> findPaginated(MarcaFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);

        Page<Marca> Pages = marcaRepository.findByFilters(
                filter.getNombre(),
                filter.getState(),
                pageable
        );

        List<MarcaDto> marcasDtos = Pages.getContent().stream()
                .map(marcaMapper::toDto)
                .toList();

        return PageResponse.<MarcaDto>builder()
                .content(marcasDtos)
                .currentPage(Pages.getNumber() + 1)
                .perPage(Pages.getSize())
                .totalPages(Pages.getTotalPages())
                .totalElements(Pages.getTotalElements())
                .build();
    }

}
