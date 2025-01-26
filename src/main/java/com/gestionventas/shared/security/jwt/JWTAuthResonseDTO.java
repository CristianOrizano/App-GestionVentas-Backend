package com.gestionventas.shared.security.jwt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestionventas.dto.usuario.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResonseDTO {
    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Lima")
    private Date expiresOn;
    private UsuarioDto usuario;
}
