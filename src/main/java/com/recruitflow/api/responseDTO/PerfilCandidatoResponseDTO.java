package com.recruitflow.api.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recruitflow.api.entities.Endereco;
import com.recruitflow.api.entities.PerfilCandidato;
import com.recruitflow.api.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PerfilCandidatoResponseDTO {

    private Long id;

    private String nome;

    private String celular;

    private String email;

    private String linkedin;

    @JsonIgnore
    private Usuario usuario;

    private Endereco endereco;
}
