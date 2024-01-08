package com.recruitflow.api.responseDTO;

import com.recruitflow.api.entities.PerfilCandidato;
import com.recruitflow.api.entities.Vaga;
import com.recruitflow.api.enums.CandidaturaStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidaturaCandidatoVagaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private VagaResponseDTO vaga;

    private CandidaturaStatus status;

    private String feedback;
}
