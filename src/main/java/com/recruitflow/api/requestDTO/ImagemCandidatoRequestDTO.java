package com.recruitflow.api.requestDTO;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagemCandidatoRequestDTO {

    private Long id;

    @Lob
    private String imagem;

    private Long idCandidato;
}
