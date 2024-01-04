package com.recruitflow.api.controllers;

import com.recruitflow.api.entities.ImagemCandidato;
import com.recruitflow.api.responseDTO.ImagemCandidatoResponseDTO;
import com.recruitflow.api.responseDTO.PerfilCandidatoResponseDTO;
import com.recruitflow.api.services.ImagemService;
import com.recruitflow.api.services.PerfilCandidatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("imagem-candidato")
@RestController("imagem-candidato")
@Tag(name = "Imagem Candidato Controller", description = "APIs relacionadas a imagem do Candidato controller")
@RequiredArgsConstructor
public class ImagemController {

    private final ImagemService imagemService;

//    @GetMapping("/{id}")
//    @Operation(summary = "Buscar a imagem do Candidato", description = "Endpoint para Buscar a imagem do Candidato")
//    public ResponseEntity<ImagemCandidatoResponseDTO> obterImagemPeloIdCandidato(@PathVariable Long id) {
//        return ResponseEntity.ok(this.imagemService.buscarImagemPorIdCandidato(id));
//    }
}
