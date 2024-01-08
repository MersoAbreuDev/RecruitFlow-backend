package com.recruitflow.api.entities;

import com.recruitflow.api.enums.CandidaturaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_candidato_candidatura_vaga")
public class CandidaturaCandidatoVaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaga")
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "perfil_candidato")
    private PerfilCandidato perfilCandidato;

    private CandidaturaStatus status;

    private String feedback;


}
