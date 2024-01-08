package com.recruitflow.api.controllers;

import com.recruitflow.api.requestDTO.PerfilCandidatoRequestDTO;
import com.recruitflow.api.requestDTO.VagaRequestDTO;
import com.recruitflow.api.responseDTO.PerfilCandidatoResponseDTO;
import com.recruitflow.api.responseDTO.VagaResponseDTO;
import com.recruitflow.api.services.ImagemService;
import com.recruitflow.api.services.PerfilCandidatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("perfil-candidatos")
@RestController
@Tag(name = "Perfil Candidato Controller", description = "APIs relacionadas a Perfil do Candidato controller")
@RequiredArgsConstructor
public class PerfilCandidatoController {

    private final PerfilCandidatoService perfilCandidatoService;

    @PostMapping
    @Operation(summary = "Cadastrar Perfil Candidato", description = "Endpoint para Cadastrar Perfil Candidato")
    public ResponseEntity<PerfilCandidatoResponseDTO> cadastrarPerfil(@RequestBody PerfilCandidatoRequestDTO perfilCandidatoRequestDTO) {
        PerfilCandidatoResponseDTO responseDTO = perfilCandidatoService.salvarPerfilCandidatoComImagemEEndereco(perfilCandidatoRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar o Perfil do Candidato por ID do Usuario", description = "Endpoint para Buscar o Perfil do Candidato por ID do Usuario")
    public ResponseEntity<PerfilCandidatoResponseDTO> buscarPerfilCandidatoPorIdUsuario(@PathVariable Long id) {
        PerfilCandidatoResponseDTO responseDTO = perfilCandidatoService.buscarCandidatoPorIdUsuario(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/buscar-candidato-id/{id}")
    @Operation(summary = "Buscar o Perfil do Candidato por ID", description = "Endpoint para Buscar o Perfil do Candidato por ID")
    public ResponseEntity<PerfilCandidatoResponseDTO> buscarPerfilCandidatoPorId(@PathVariable Long id) {
        PerfilCandidatoResponseDTO responseDTO = perfilCandidatoService.buscarPerfilCandidatoPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera Perfil do Candidato", description = "Endpoint para Alterar o Perfil do Candidato")
    public ResponseEntity<PerfilCandidatoResponseDTO> atualizaPerfilCandidato(@PathVariable Long id, @RequestBody PerfilCandidatoRequestDTO perfilCandidatoRequestDTO) {
        PerfilCandidatoResponseDTO responseDTO = perfilCandidatoService.alterarPerfilCandidato(id, perfilCandidatoRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
