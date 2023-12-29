package com.recruitflow.api.requestDTO;

import com.recruitflow.api.enums.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VagaRequestDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;

        private String titulo;

        private String descricao;

        private Status status;
}
