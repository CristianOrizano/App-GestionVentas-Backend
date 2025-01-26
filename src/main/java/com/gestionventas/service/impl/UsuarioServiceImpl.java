package com.gestionventas.service.impl;

import com.gestionventas.domain.Categoria;
import com.gestionventas.domain.Rol;
import com.gestionventas.domain.Usuario;
import com.gestionventas.dto.usuario.UsuarioDto;
import com.gestionventas.dto.usuario.UsuarioSaveDto;
import com.gestionventas.dto.usuario.mapper.UsuarioMapper;
import com.gestionventas.repository.RolRepository;
import com.gestionventas.repository.UsuarioRepository;
import com.gestionventas.service.IUsuarioService;
import com.gestionventas.shared.exeption.ResourceNotFoundException;
import com.gestionventas.shared.security.jwt.JWTAuthResonseDTO;
import com.gestionventas.shared.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
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
        return null;
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
}
