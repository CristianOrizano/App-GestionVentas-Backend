package com.gestionventas.service.impl;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Cliente;
import com.gestionventas.dto.categoria.CategoriaDto;
import com.gestionventas.dto.cliente.ClienteDto;
import com.gestionventas.dto.cliente.ClienteFilterDto;
import com.gestionventas.dto.cliente.ClienteSaveDto;
import com.gestionventas.dto.cliente.mapper.ClienteMapper;
import com.gestionventas.repository.ClienteRepository;
import com.gestionventas.service.IClienteService;
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
public class ClienteServiceImpl implements IClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteDto> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    @Override
    public ClienteDto findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDto)
                .orElseThrow(() ->  new ResourceNotFoundException("Cliente no encontrada con ID: " + id));
    }

    @Override
    public ClienteDto create(ClienteSaveDto clienteBody) {
        Cliente cliente = clienteMapper.toEntity(clienteBody);
        cliente.setState(true);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDto update(Long id, ClienteSaveDto clienteBody) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Cliente no encontrada con ID: " + id));
        clienteMapper.updateEntity(clienteBody,cliente);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDto disable(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Cliente no encontrada con ID: " + id));
        cliente.setState(!cliente.getState());
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Override
    public PageResponse<ClienteDto> findPaginated(ClienteFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);

        Page<Cliente> Pages = clienteRepository.findByFilters(
                filter.getNombre(),
                filter.getApellido(),
                filter.getDireccion(),
                filter.getNdocumento(),
                filter.getTelefono(),
                filter.getState(),
                pageable
        );

        List<ClienteDto> clienteDtos = Pages.getContent().stream()
                .map(clienteMapper::toDto)
                .toList();

        return PageResponse.<ClienteDto>builder()
                .content(clienteDtos)
                .currentPage(Pages.getNumber() + 1)
                .perPage(Pages.getSize())
                .totalPages(Pages.getTotalPages())
                .totalElements(Pages.getTotalElements())
                .build();
    }
}
