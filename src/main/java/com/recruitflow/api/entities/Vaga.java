package com.recruitflow.api.entities;

import com.recruitflow.api.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_vaga")
@SequenceGenerator(name = "SQ_VAGA", allocationSize = 1, sequenceName = "SQ_VAGA")
public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VAGA")
    private Long id;

    private String titulo;

    private String descricao;

    private Status status;
}
