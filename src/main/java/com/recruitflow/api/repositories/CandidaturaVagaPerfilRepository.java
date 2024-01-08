package com.recruitflow.api.repositories;

import com.recruitflow.api.entities.CandidaturaCandidatoVaga;
import com.recruitflow.api.entities.PerfilCandidato;
import com.recruitflow.api.responseDTO.CandidaturaVagaResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CandidaturaVagaPerfilRepository extends JpaRepository<CandidaturaCandidatoVaga, Long> {

    @Query("SELECT c.vaga.id, COUNT(DISTINCT c.perfilCandidato.id) FROM CandidaturaCandidatoVaga c GROUP BY c.vaga.id")
    List<Object> contarPerfilCandidatoPorVaga();

    @Query("SELECT cv FROM CandidaturaCandidatoVaga cv WHERE cv.perfilCandidato.id = :perfilCandidatoId")
    List<CandidaturaCandidatoVaga> buscarCandidaturasPorPerfilCandidatoId(Long perfilCandidatoId);

    @Query("SELECT c FROM CandidaturaCandidatoVaga c JOIN c.perfilCandidato WHERE c.vaga.id = :vagaId")
    List<CandidaturaCandidatoVaga> buscarCandidatosPorIdVaga(Long vagaId);

    boolean existsByPerfilCandidatoIdAndVagaId(Long idPerfilCandidato, Long idVaga);
}
