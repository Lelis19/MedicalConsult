package br.com.tiagooliveira.medicalconsult.usuario.domain;

import lombok.Getter;

@Getter
public enum Permissao {
    ADMIN("ADMIN"),
    SECRETARIO("SECRETARIO"),
    PACIENTE("PACIENTE");

    private final String descricao;

    Permissao(String descricao) {
        this.descricao = descricao;
    }
}


