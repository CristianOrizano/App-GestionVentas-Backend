package com.gestionventas.service.impl;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Ciudad;
import com.gestionventas.domain.Empleado;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.categoria.CategoriaFilterDto;
import com.gestionventas.dto.categoria.CategoriaSaveDto;
import com.gestionventas.dto.categoria.mapper.CategoriaMapper;
import com.gestionventas.dto.ciudad.mapper.CiudadMapper;
import com.gestionventas.dto.empleado.EmpleadoDto;
import com.gestionventas.repository.CategoriaRepository;
import com.gestionventas.repository.CiudadRepository;
import com.gestionventas.service.ICategoriaService;
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
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll().stream()
                .filter(categoria -> Boolean.TRUE.equals(categoria.getState())) // Filtra categorías activas
                .map(categoriaMapper::toDto)                                   // Mapea a DTOs
                .toList();                                                     // Convierte a lista
    }

    @Override
    public CategoriaDto findById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDto)
                .orElseThrow(() ->  new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
    }

    @Override
    public CategoriaDto create(CategoriaSaveDto categoriaBody) {
        Categoria categoria = categoriaMapper.toEntity(categoriaBody);
        categoria.setState(true);
        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDto update(Long id, CategoriaSaveDto categoriaBody) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
        categoriaMapper.updateEntity(categoriaBody,categoria);
        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDto disable(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
        categoria.setState(!categoria.getState());
        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Override
    public PageResponse<CategoriaDto> findPaginated(CategoriaFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);

        Page<Categoria> Pages = categoriaRepository.findByFilters(
                filter.getNombre(),
                filter.getDescripcion(),
                filter.getState(),
                pageable
        );

        List<CategoriaDto> empleadosDtos = Pages.getContent().stream()
                .map(categoriaMapper::toDto)
                .toList();

        return PageResponse.<CategoriaDto>builder()
                .content(empleadosDtos)
                .currentPage(Pages.getNumber() + 1)
                .perPage(Pages.getSize())
                .totalPages(Pages.getTotalPages())
                .totalElements(Pages.getTotalElements())
                .build();
    }
}
