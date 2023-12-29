package com.recruitflow.api.requestDTO;

import com.recruitflow.api.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String email;

    private  String senha;
}
