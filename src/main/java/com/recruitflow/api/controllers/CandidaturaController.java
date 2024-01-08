package com.recruitflow.api.controllers;


import com.recruitflow.api.requestDTO.CandidaturaCandidatoVagaRequestDTO;
import com.recruitflow.api.responseDTO.CandidaturaCandidatoVagaResponseDTO;
import com.recruitflow.api.responseDTO.CandidaturaVagaResponseDTO;
import com.recruitflow.api.responseDTO.VagaResponseDTO;
import com.recruitflow.api.services.CandidaturaVagaPerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("candidatar")
@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Candidatura Controller", description = "APIs relacionadas a Candidatura do Candidato e Vaga controller")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CandidaturaController {

    private final CandidaturaVagaPerfilService candidaturaVagaPerfilService;


    @PostMapping
    public ResponseEntity<String> candidatura(@RequestBody CandidaturaCandidatoVagaRequestDTO candidaturaCandidatoVagaRequestDTO) {
        this.candidaturaVagaPerfilService.canditar(candidaturaCandidatoVagaRequestDTO);
        return ResponseEntity.ok("Sucesso!");
    }

    @PostMapping("avaliar")
    public ResponseEntity<Void> avaliarCandidato(@RequestBody CandidaturaCandidatoVagaRequestDTO candidaturaCandidatoVagaRequestDTO) {
        this.candidaturaVagaPerfilService.avaliarCandidatura(candidaturaCandidatoVagaRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/candidatos-por-vaga")
    public ResponseEntity<List<Object>> candidatosPorVaga() {
        List<Object> objects = candidaturaVagaPerfilService.quantidadeCandidatosPorvaga();
        return ResponseEntity.ok(objects);
    }

    @GetMapping("/minhas-candidaturas/{id}")
    @Operation(summary = "Buscar Candidaturas por ID", description = "Endpoint para Buscar Candidaturas por Id")
    public ResponseEntity<List<CandidaturaCandidatoVagaResponseDTO>> buscarVagasPorIdCandidato(@PathVariable Long id) {
        return ResponseEntity.ok(this.candidaturaVagaPerfilService.buscarCandidaturasPorIdCandidato(id));
    }

    @GetMapping("/candidatos-por-vaga/{id}")
    @Operation(summary = "Buscar Candidatos por ID da vaga", description = "Endpoint para Buscar Candidatos por Id da vaga")
    public ResponseEntity<List<CandidaturaVagaResponseDTO>> buscarCandidaturasPorIdVaga(@PathVariable Long id) {
        return ResponseEntity.ok(this.candidaturaVagaPerfilService.buscarCandidatosPorIdVaga(id));
    }

}
