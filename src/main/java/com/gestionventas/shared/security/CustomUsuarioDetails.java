package com.gestionventas.shared.security;

import com.gestionventas.domain.Usuario;
import com.gestionventas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUsuarioDetails implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // Puedes devolver toda la información que necesitas sin limitarte a los campos básicos (username, password, authorities).
    //  Si necesitas agregar más atributos, puedes extender fácilmente la clase sin afectar el resto del sistema.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new UsuarioDetails(usuario);
    }
   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toSet());

        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    } */
}
