package com.recruitflow.api.services;

import com.recruitflow.api.repositories.UsuarioRepository;
import com.recruitflow.api.configs.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private final PasswordEncoder passwordEncod;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    public static final String LINK_TOKEN_RESETAR_SENHA = "";

}
