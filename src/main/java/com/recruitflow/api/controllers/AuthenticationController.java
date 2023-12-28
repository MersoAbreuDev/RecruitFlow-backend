package com.recruitflow.api.controllers;

import com.recruitflow.api.requestDTO.AuthenticationRequestDTO;
import com.recruitflow.api.requestDTO.RegisterRequestDTO;
import com.recruitflow.api.responseDTO.AuthenticationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.recruitflow.api.services.AuthenticationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller", description = "APIs relacionadas à autenticação")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    @Operation(summary = "Cadastrar Usuario", description = "Endpoint para cadastrar um novo usuário")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO requestDTO) {
        return ResponseEntity.ok(service.register(requestDTO));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Autenticar Usuario e gerar o token", description = "Endpoint para autenticar o usuário e gerar o toekn")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO requestDTO) {
        return ResponseEntity.ok(service.authenticate(requestDTO));
    }
}
