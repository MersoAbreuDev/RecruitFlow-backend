package com.recruitflow.api.requestDTO;

import com.recruitflow.api.enums.CandidaturaStatus;
import com.recruitflow.api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidaturaCandidatoVagaRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idVaga;

    private Long idPerfilCandidato;

    private CandidaturaStatus status;

    private String feedback;

}
