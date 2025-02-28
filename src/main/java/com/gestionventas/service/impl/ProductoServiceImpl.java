package com.gestionventas.service.impl;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Empleado;
import com.gestionventas.domain.Producto;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.dto.producto.ProductoFilterDto;
import com.gestionventas.dto.producto.ProductoSaveDto;
import com.gestionventas.dto.producto.mapper.ProductoMapper;
import com.gestionventas.repository.CategoriaRepository;
import com.gestionventas.repository.ProductoRepository;
import com.gestionventas.service.IProductoService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import com.gestionventas.shared.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDto> findAll() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoDto findById(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto)
                .orElseThrow(() ->  new ResourceNotFoundException("Producto no encontrada con ID: " + id));
    }

    @Override
    public ProductoDto create(ProductoSaveDto productoBody) {
        categoriaRepository.findById(productoBody.getIdCategoria())
                .orElseThrow(() ->  new ResourceNotFoundException("Producto no encontrada con ID: " + productoBody.getIdCategoria()));

        Producto producto = productoMapper.toEntity(productoBody);
        producto.setState(true);
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public ProductoDto update(Long id, ProductoSaveDto productoBody) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Producto no encontrada con ID: " + id));

        categoriaRepository.findById(productoBody.getIdCategoria())
                .orElseThrow(() ->  new ResourceNotFoundException("Categoria no encontrada con ID: " + id));

        productoMapper.updateEntity(productoBody,producto);
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public ProductoDto disable(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Producto no encontrada con ID: " + id));
        producto.setState(!producto.getState());
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public PageResponse<ProductoDto> findPaginated(ProductoFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);

        Page<Producto> Pages = productoRepository.findByFilters(
                filter.getDescripcion(),
                filter.getStock(),
                filter.getPrecio(),
                filter.getNimagen(),
                filter.getIdCategoria(),
                filter.getIdMarca(),
                filter.getState(),
                pageable
        );

        List<ProductoDto> productosDtos = Pages.getContent().stream()
                .map(productoMapper::toDto)
                .toList();

        return PageResponse.<ProductoDto>builder()
                .content(productosDtos)
                .currentPage(Pages.getNumber() + 1)
                .perPage(Pages.getSize())
                .totalPages(Pages.getTotalPages())
                .totalElements(Pages.getTotalElements())
                .build();
    }
}
