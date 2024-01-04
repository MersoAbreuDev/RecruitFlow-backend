package com.recruitflow.api.services;

import com.recruitflow.api.entities.Usuario;
import com.recruitflow.api.enums.Role;
import com.recruitflow.api.handlers.BadRequestException;
import com.recruitflow.api.repositories.UsuarioRepository;
import com.recruitflow.api.requestDTO.AuthenticationRequestDTO;
import com.recruitflow.api.requestDTO.RegisterRequestDTO;
import com.recruitflow.api.responseDTO.AuthenticationResponseDTO;
import com.recruitflow.api.configs.security.jwt.JwtService;
import com.recruitflow.api.responseDTO.VagaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final ModelMapper modelMapper;

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
                .role(usuario.getRole())
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
        Long id = user.getId();
        String role = String.valueOf(user.getRole());
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .id(id)
                .role(Role.valueOf(role))
                .nome(nome)
                .build();
    }


    public List<AuthenticationResponseDTO> getAllUser() {
        return this.repository.findAll().stream().map(user -> this.modelMapper.map(user, AuthenticationResponseDTO.class)).collect(Collectors.toList());
    }
}
