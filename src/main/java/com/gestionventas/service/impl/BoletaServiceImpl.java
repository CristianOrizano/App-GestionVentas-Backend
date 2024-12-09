package com.gestionventas.service.impl;


import com.gestionventas.domain.*;
import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.boleta.BoletaSaveDto;
import com.gestionventas.dto.boleta.DetalleBoletaDto;
import com.gestionventas.dto.boleta.mapper.BoletaMapper;
import com.gestionventas.dto.empleado.mapper.EmpleadoMapper;
import com.gestionventas.repository.*;
import com.gestionventas.service.IBoletaService;
import com.gestionventas.service.IClienteService;
import com.gestionventas.service.IUsuarioService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoletaServiceImpl implements IBoletaService {

    private final BoletaMapper boletaMapper;
    private final IClienteService IClienteService;
    private final IUsuarioService IUsuarioService;
    private final BoletaRepository boletaRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    @Override
    public Map<String, Object> registrarBoleta(BoletaSaveDto bean) {
        IClienteService.findById(bean.getIdCliente());
        IUsuarioService.findById(bean.getIdUsuario());
        Boleta boleta = boletaMapper.toEntity(bean);
        // Establecer la relación entre boleta y detalles de forma automática
        boleta.getDetalles().forEach(detalle -> detalle.setBoleta(boleta));
        boletaRepository.save(boleta);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Orden guardada exitosamente");

        return response;

    }

    @Override
    public List<BoletaDto> findAll() {
        return null;
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
