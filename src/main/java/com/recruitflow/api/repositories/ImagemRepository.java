package com.recruitflow.api.repositories;

import com.recruitflow.api.entities.ImagemCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ImagemRepository extends JpaRepository<ImagemCandidato, Long> {

    @Query("SELECT ic FROM ImagemCandidato ic WHERE ic.perfilCandidato.id = :candidatoId")
    Optional<ImagemCandidato> findByImagemCandidatoFromIdPerfilCandidato(@Param("candidatoId") Long candidatoId);
}
