package com.recruitflow.api.services;

import com.recruitflow.api.entities.CandidaturaCandidatoVaga;
import com.recruitflow.api.entities.PerfilCandidato;
import com.recruitflow.api.entities.Vaga;
import com.recruitflow.api.enums.CandidaturaStatus;
import com.recruitflow.api.handlers.BadRequestException;
import com.recruitflow.api.handlers.CandidaturaException;
import com.recruitflow.api.handlers.PerfilNotFoundException;
import com.recruitflow.api.repositories.CandidaturaVagaPerfilRepository;
import com.recruitflow.api.requestDTO.CandidaturaCandidatoVagaRequestDTO;
import com.recruitflow.api.responseDTO.CandidaturaCandidatoVagaResponseDTO;
import com.recruitflow.api.responseDTO.CandidaturaVagaResponseDTO;
import com.recruitflow.api.responseDTO.PerfilCandidatoResponseDTO;
import com.recruitflow.api.responseDTO.VagaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidaturaVagaPerfilService {

    private final ModelMapper modelMapper;

    private final PerfilCandidatoService perfilCandidatoService;

    private final VagaService vagaService;

    private final CandidaturaVagaPerfilRepository candidaturaVagaPerfilRepository;

    public void canditar(CandidaturaCandidatoVagaRequestDTO candidaturaCandidatoVagaRequestDTO) {

        Optional<PerfilCandidatoResponseDTO> perfilOptional = Optional.ofNullable(this.perfilCandidatoService.buscarPerfilCandidatoPorId(candidaturaCandidatoVagaRequestDTO.getIdPerfilCandidato()));
        if (perfilOptional == null || perfilOptional.isEmpty()) {
            throw new PerfilNotFoundException("Atualize seu perfil para realizar candidatura.");
        }

        if (candidatoJaSeCandidatou(candidaturaCandidatoVagaRequestDTO.getIdPerfilCandidato(), candidaturaCandidatoVagaRequestDTO.getIdVaga())) {
            throw new CandidaturaException("Candidato já se candidatou para esta vaga.");
        }

        PerfilCandidatoResponseDTO perfilCandidatoResponseDTO = this.perfilCandidatoService.buscarPerfilCandidatoPorId(candidaturaCandidatoVagaRequestDTO.getIdPerfilCandidato());
        VagaResponseDTO vagaResponseDTO = this.vagaService.buscarVagaPorId(candidaturaCandidatoVagaRequestDTO.getIdVaga());

        PerfilCandidato perfilCandidato = this.modelMapper.map(perfilCandidatoResponseDTO, PerfilCandidato.class);
        Vaga vaga = this.modelMapper.map(vagaResponseDTO, Vaga.class);

        CandidaturaCandidatoVaga candidaturaCandidatoVaga = CandidaturaCandidatoVaga.builder().perfilCandidato(perfilCandidato).vaga(vaga).build();
        candidaturaCandidatoVaga.setStatus(CandidaturaStatus.CANDIDATO);
        this.candidaturaVagaPerfilRepository.save(candidaturaCandidatoVaga);
    }

    private boolean candidatoJaSeCandidatou(Long idPerfilCandidato, Long idVaga) {
        return this.candidaturaVagaPerfilRepository.existsByPerfilCandidatoIdAndVagaId(idPerfilCandidato, idVaga);
    }

    public void avaliarCandidatura(CandidaturaCandidatoVagaRequestDTO candidaturaCandidatoVagaRequestDTO) {
        CandidaturaCandidatoVaga candidaturaCandidatoVaga = this.candidaturaVagaPerfilRepository.findById(candidaturaCandidatoVagaRequestDTO.getIdVaga()).get();
        candidaturaCandidatoVaga.setFeedback(candidaturaCandidatoVagaRequestDTO.getFeedback());
        candidaturaCandidatoVaga.setStatus(candidaturaCandidatoVagaRequestDTO.getStatus());
        CandidaturaCandidatoVagaRequestDTO requestDTO = this.modelMapper.map(candidaturaCandidatoVaga, CandidaturaCandidatoVagaRequestDTO.class);
        this.salvar(requestDTO);
    }

    public void salvar(CandidaturaCandidatoVagaRequestDTO candidaturaCandidatoVagaRequestDTO) {
        CandidaturaCandidatoVaga candidaturaCandidatoVaga = this.modelMapper.map(candidaturaCandidatoVagaRequestDTO, CandidaturaCandidatoVaga.class);
        this.candidaturaVagaPerfilRepository.save(candidaturaCandidatoVaga);
    }

    public List<Object> quantidadeCandidatosPorvaga() {
        return this.candidaturaVagaPerfilRepository.contarPerfilCandidatoPorVaga();
    }

    public List<CandidaturaCandidatoVagaResponseDTO> buscarCandidaturasPorIdCandidato(Long id) {
        return this.candidaturaVagaPerfilRepository.buscarCandidaturasPorPerfilCandidatoId(id)
                .stream().map(vaga -> this.modelMapper.map(vaga, CandidaturaCandidatoVagaResponseDTO.class)
                ).collect(Collectors.toList());
    }

    public List<CandidaturaVagaResponseDTO> buscarCandidatosPorIdVaga(Long id) {
        return this.candidaturaVagaPerfilRepository.buscarCandidatosPorIdVaga(id)
                .stream().map(candidatos -> this.modelMapper.map(candidatos, CandidaturaVagaResponseDTO.class)
                ).collect(Collectors.toList());
    }

}
