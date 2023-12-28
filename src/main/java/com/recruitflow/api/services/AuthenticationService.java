package com.recruitflow.api.services;

import com.recruitflow.api.entities.Usuario;
import com.recruitflow.api.enums.Role;
import com.recruitflow.api.handlers.BadRequestException;
import com.recruitflow.api.repositories.UsuarioRepository;
import com.recruitflow.api.requestDTO.AuthenticationRequestDTO;
import com.recruitflow.api.requestDTO.RegisterRequestDTO;
import com.recruitflow.api.responseDTO.AuthenticationResponseDTO;
import com.recruitflow.api.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(RegisterRequestDTO requestDTO) {

        this.repository.findByEmail(requestDTO.getEmail()).ifPresent(usuario -> {
            throw new BadRequestException("Email já cadastrado.");
        });

        var usuario = Usuario.builder()
                .email(requestDTO.getEmail())
                .senha(passwordEncoder.encode(requestDTO.getSenha()))
                .role(Role.buscarRole(requestDTO.getRole()))
                .nome(requestDTO.getNome())
                .build();
        repository.save(usuario);

        var jwtToken = jwtService.generateToken(usuario);
        return  AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .nome(usuario.getNome())
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO requestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getEmail(),
                        requestDTO.getSenha()
                )
        );
        var user = repository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
        String nome = user.getNome();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .nome(nome)
                .build();
    }
}
