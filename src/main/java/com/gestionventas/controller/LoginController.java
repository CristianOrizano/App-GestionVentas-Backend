package com.gestionventas.controller;

import com.gestionventas.dto.usuario.LoginDto;
import com.gestionventas.shared.security.jwt.JWTAuthResonseDTO;
import com.gestionventas.shared.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirements(value = {})
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/login")
@Tag(name = "Login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDto loginDTO){
        // sirve para verificar las credenciales del usuario
        // Comprueba si las credenciales son correctas utilizando la configuración de autenticación definidas
        // por, un UserDetailsService y un PasswordEncoder como BCrypt.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

       // Obtener el principal (detalles del usuario)
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("============> "+ userDetails.getUsername());

        //obtenemos el token del jwtTokenProvider
        JWTAuthResonseDTO token = jwtTokenProvider.getSecurity(authentication);
        return ResponseEntity.ok(token);
    }



}
