package com.gestionventas.service.impl;

import com.gestionventas.domain.*;
import com.gestionventas.dto.boleta.BoletaDto;
import com.gestionventas.dto.usuario.UsuarioDto;
import com.gestionventas.dto.usuario.UsuarioFilterDto;
import com.gestionventas.dto.usuario.UsuarioSaveDto;
import com.gestionventas.dto.usuario.mapper.UsuarioMapper;
import com.gestionventas.repository.RolRepository;
import com.gestionventas.repository.UsuarioRepository;
import com.gestionventas.service.IUsuarioService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import com.gestionventas.shared.page.PageResponse;
import com.gestionventas.shared.security.jwt.JWTAuthResonseDTO;
import com.gestionventas.shared.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    public UsuarioDto findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .orElseThrow(() ->  new ResourceNotFoundException("Usuario no encontrada con ID: " + id));
    }

    @Override
    public UsuarioDto create(UsuarioSaveDto usuarioBody) {
        Usuario usuario = usuarioMapper.toEntity(usuarioBody);
        // Lógica avanzada: Obtener roles desde la base de datos
        Set<Rol> roles = usuarioBody.getRoles().stream()
                .map(id -> rolRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + id)))
                .collect(Collectors.toSet());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setState(true);
        usuario.setRoles(roles);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDto update(Long id, UsuarioSaveDto usuarioBody) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Usuario no encontrada con ID: " + id));
        usuarioMapper.updateEntity(usuarioBody,usuario);

        Set<Rol> roles = usuarioBody.getRoles().stream()
                .map(idR -> rolRepository.findById(idR)
                        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + idR)))
                .collect(Collectors.toSet());
        usuario.setRoles(roles);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDto disable(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Usuario no encontrada con ID: " + id));
        usuario.setState(!usuario.getState());
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public JWTAuthResonseDTO login(Authentication auth) {
        JWTAuthResonseDTO security = new JWTAuthResonseDTO();
        Usuario user = usuarioRepository.findByUsername(auth.getName());

        String token = jwtTokenProvider.generarToken(auth);
        security.setTokenDeAcceso(token);
        security.setExpiresOn(jwtTokenProvider.getExpirationDateFromToken(token));
        security.setUsuario(usuarioMapper.toDto(user));
        return security;
    }

    @Override
    public PageResponse<UsuarioDto> findPaginated(UsuarioFilterDto filter) {
        Sort sort = filter.getSortDir().equalsIgnoreCase("asc") ? Sort.by(filter.getSortBy()).ascending() : Sort.by(filter.getSortBy()).descending();
        Pageable pageable = PageRequest.of(filter.getPage()- 1, filter.getSize(),sort);

        Page<Usuario> Pages = usuarioRepository.findByFilters(
                filter.getNombre(),
                filter.getApellido(),
                filter.getState(),
                pageable
        );

        List<UsuarioDto> boletasDtos = Pages.getContent().stream()
                .map(usuarioMapper::toDto)
                .toList();

        return PageResponse.<UsuarioDto>builder()
                .content(boletasDtos)
                .currentPage(Pages.getNumber() + 1)
                .perPage(Pages.getSize())
                .totalPages(Pages.getTotalPages())
                .totalElements(Pages.getTotalElements())
                .build();
    }
}
