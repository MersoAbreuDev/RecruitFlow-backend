package com.recruitflow.api.responseDTO;

import com.recruitflow.api.enums.CandidaturaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidaturaVagaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCandidato;

    private CandidaturaStatus status;

    private PerfilCandidatoResponseDTO perfilCandidato;

    private String feedback;

}
