package com.recruitflow.api.controllers;

import com.recruitflow.api.requestDTO.VagaRequestDTO;
import com.recruitflow.api.responseDTO.VagaResponseDTO;
import com.recruitflow.api.services.VagaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("vagas")
@RestController
@Tag(name = "Vagas Controller", description = "APIs relacionadas a Vagas controller")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class VagasController {

    private final VagaService vagaService ;

    @GetMapping
    @Operation(summary = "Listar vagas", description = "Endpoint para lista das vagas")
    public ResponseEntity<List<VagaResponseDTO>> getReceitas(@RequestParam Integer pagina,
                                                             @RequestParam Integer quantidade,
                                                             @RequestParam String ordem,
                                                             @RequestParam String ordenarPor) {

        return new ResponseEntity<List<VagaResponseDTO>>(
                this.vagaService.buscarTodos(PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem), ordenarPor))), HttpStatus.OK);

    }
    @PostMapping
    @Operation(summary = "Cadastrar Vagas", description = "Endpoint para Cadastrar vagas")
    public ResponseEntity<VagaResponseDTO> cadastrarVagas(@RequestBody VagaRequestDTO vagaRequestDTO){
        return ResponseEntity.ok(this.vagaService.salvar(vagaRequestDTO));
    }
}
