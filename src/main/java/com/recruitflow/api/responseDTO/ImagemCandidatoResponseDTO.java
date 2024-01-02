package com.recruitflow.api.responseDTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagemCandidatoResponseDTO {

    private Long id;

    @Lob
    private byte[] imagem;

    private Long idCandidato;
}
