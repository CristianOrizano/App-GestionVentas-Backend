package com.proyectojwt.controller;

import com.proyectojwt.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectojwt.dto.LoginDTO;
import com.proyectojwt.jwt.JWTAuthResonseDTO;
import com.proyectojwt.jwt.JwtTokenProvider;

import java.time.Clock;

@RestController
@RequestMapping("/services/auth")
@CrossOrigin("*")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	@PostMapping("/signin")
	public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
		//authenticationManager iniciar proceso de Auth
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

		System.out.println("============> "+ authentication.getPrincipal());
		Usuario usuario = (Usuario)authentication.getPrincipal();
		System.out.println("============> "+ usuario.getUsername());

		//le estamos indicando que el usuario ya esta autenticado 
		//apartir de ahora aplicar autorizacion basada en roles
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//obtenemos el token del jwtTokenProvider
		JWTAuthResonseDTO token = jwtTokenProvider.getSecurity(authentication);
		
		return ResponseEntity.ok(token);
	}
	
	
}
