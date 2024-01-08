package com.recruitflow.api.enums;

public enum CandidaturaStatus {
    CANDIDATO("CANDIDATO"),
    DESCLASSIFICADO("DESCLASSIFICADO"),
    CLASSIFICADO("CLASSIFICADO");

    private String descricao;

    private CandidaturaStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
