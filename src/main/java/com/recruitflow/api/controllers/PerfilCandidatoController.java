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

@RequestMapping("perfil-candidatos")
@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Perfil Candidato Controller", description = "APIs relacionadas a Perfil do Candidato controller")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PerfilCandidatoController {

    private final PerfilCandidatoService perfilCandidatoService;



    @PostMapping
    @Operation(summary = "Cadastrar Perfil Candidato", description = "Endpoint para Cadastrar Perfil Candidato")
    public ResponseEntity<PerfilCandidatoResponseDTO> cadastrarPerfil(@RequestBody PerfilCandidatoRequestDTO perfilCandidatoRequestDTO) {
        byte[] imagemBytes = new byte[0];
        PerfilCandidatoResponseDTO responseDTO = this.perfilCandidatoService.salvarPerfilCandidatoComImagemEEndereco(perfilCandidatoRequestDTO, imagemBytes);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar o Perfil do Candidato", description = "Endpoint para Buscar o Perfil do Candidato")
    public ResponseEntity<PerfilCandidatoResponseDTO> obterPerfilCandidatoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.perfilCandidatoService.buscarCandidatoPorIdUsuario(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera Perfil do Candidato", description = "Endpoint para Alterar o Perfil do Candidato")
    public ResponseEntity<PerfilCandidatoResponseDTO> atualizaPerfilCandidato(@PathVariable Long id, @RequestBody PerfilCandidatoRequestDTO perfilCandidatoRequestDTO) {
        return ResponseEntity.ok(this.perfilCandidatoService.alterarPerfilCandidato(id, perfilCandidatoRequestDTO));
    }
}
