package com.recruitflow.api.requestDTO;

import com.recruitflow.api.entities.Endereco;
import com.recruitflow.api.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PerfilCandidatoRequestDTO {

    private Long id;

    private String nome;

    private String celular;

    private String email;

    private Usuario usuario;

    private String linkedin;

    private Endereco endereco;
}
