package com.gestionventas.service.impl;


import com.gestionventas.domain.*;
import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaFilterDto;
import com.gestionventas.dto.boleta.BoletaSaveDto;
import com.gestionventas.dto.boleta.DetalleBoletaDto;
import com.gestionventas.dto.boleta.mapper.BoletaMapper;
import com.gestionventas.dto.empleado.mapper.EmpleadoMapper;
import com.gestionventas.dto.producto.ProductoDto;
import com.gestionventas.dto.producto.ProductoFilterDto;
import com.gestionventas.repository.*;
import com.gestionventas.service.IBoletaService;
import com.gestionventas.service.IClienteService;
import com.gestionventas.service.INotificacionService;
import com.gestionventas.service.IUsuarioService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import com.gestionventas.shared.page.PageResponse;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BoletaServiceImpl implements IBoletaService {

    private final BoletaMapper boletaMapper;
    private final IClienteService clienteService;
    private final IUsuarioService usuarioService;
    private final BoletaRepository boletaRepository;
    private final INotificacionService notificacionService;
    private final ProductoRepository productoRepository;

    @Transactional
    @Override
    public Map<String, Object> registrarBoleta(BoletaSaveDto bean) {
        clienteService.findById(bean.getIdCliente());
        usuarioService.findById(bean.getIdUsuario());
        Boleta boleta = boletaMapper.toEntity(bean);
        boleta.setFechaEmision(LocalDateTime.now());
        // Validar stock y actualizarlo
        for (DetalleBoletaDto detalleDto : bean.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDto.getIdProducto())
                    .orElseThrow(() ->  new ResourceNotFoundException("Producto no encontrada con ID: " + detalleDto.getIdProducto()));

            if (producto.getStock() < detalleDto.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto ID: " + producto.getId());
            }
            // Descontar stock
            producto.setStock(producto.getStock() - detalleDto.getCantidad());
           Producto prod= productoRepository.save(producto);
            if (prod.getStock() < 10) {
                notificacionService.notificacion(producto.getDescripcion()+", poco stock",producto.getId());
            }
        }
        // Establecer la relación entre boleta y detalles de forma automática
        boleta.getDetalles().forEach(detalle -> detalle.setBoleta(boleta));
        boletaRepository.save(boleta);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Orden guardada exitosamente");
        return response;
    }

    @Override
    public List<BoletaDto> findAll() {
        return boletaRepository.findAll().stream()
                .map(boletaMapper::toDto)
                .toList();
    }

    @Override
    public PageResponse<BoletaDto> findPaginated(BoletaFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);

        Page<Boleta> Pages = boletaRepository.findAll(pageable);

        List<BoletaDto> boletasDtos = Pages.getContent().stream()
                .map(boletaMapper::toDto)
                .toList();

        return PageResponse.<BoletaDto>builder()
                .content(boletasDtos)
                .currentPage(Pages.getNumber() + 1)
                .perPage(Pages.getSize())
                .totalPages(Pages.getTotalPages())
                .totalElements(Pages.getTotalElements())
                .build();
    }

    @Override
    public List<BoletaDto> obtenerBoletasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return boletaRepository.findByFechaEmisionBetween(fechaInicio, fechaFin).stream()
                .map(boletaMapper::toDto)
                .toList();
    }

    /*
    @Override
    public BoletaDto registrarBoleta(BoletaSaveDto bean) {
       Cliente cliente= clienteRepository.findById(bean.getIdCliente())
                .orElseThrow(() ->  new ResourceNotFoundException("Cliente no encontrada con ID: " + bean.getIdCliente()));
        Usuario usuario = usuarioRepository.findById(bean.getIdUsuario())
                .orElseThrow(() ->  new ResourceNotFoundException("Usuario no encontrada con ID: " + bean.getIdUsuario()));

        Boleta boleta = boletaMapper.toEntity(bean);
        boleta.setCliente(cliente);
        boleta.setUsuario(usuario);

        List<DetalleBoleta> detalles = new ArrayList<>();
        for (DetalleBoletaDto detalleDto : bean.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDto.getIdProducto())
                    .orElseThrow(() ->  new ResourceNotFoundException("Producto no encontrada con ID: " + detalleDto.getIdProducto()));

            DetalleBoleta detalleBoleta = new DetalleBoleta();
            detalleBoleta.setBoleta(boleta); // Asocia la boleta a cada detalle
            detalleBoleta.setProducto(producto);
            detalleBoleta.setCantidad(detalleDto.getCantidad());
            detalles.add(detalleBoleta);
        }
        boleta.setDetalles(detalles); // Establecer la lista de detalles en la boleta

        // Guardar la boleta con los detalles asociados
        return boletaMapper.toDto(boletaRepository.save(boleta));
    }
    */
}
