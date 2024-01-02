package com.recruitflow.api.repositories;

import com.recruitflow.api.entities.PerfilCandidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilCandidatoRepository extends JpaRepository<PerfilCandidato, Long> {

    Optional<PerfilCandidato> findByUsuarioId(Long usuarioId);
}
