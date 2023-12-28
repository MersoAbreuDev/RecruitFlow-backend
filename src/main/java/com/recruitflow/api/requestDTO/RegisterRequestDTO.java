package com.recruitflow.api.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String role;
}
