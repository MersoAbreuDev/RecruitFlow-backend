package com.recruitflow.api.enums;

public enum Status {
    ATIVA("ATIVA"),
    CANCELADA("CANCELADA"),
    ANDAMENTO("ANDAMENTO"),
    FECHADA("FECHADA"),
    REVISAO("REVISÃO");

    private String descricao;

    private Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
