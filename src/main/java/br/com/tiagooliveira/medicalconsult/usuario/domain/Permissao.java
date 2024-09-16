package br.com.tiagooliveira.medicalconsult.usuario.domain;

public enum Permissao {
    ADMIN("ADMIN"),
    SECRETARIO("SECRETARIO");

    private final String descricao;

    Permissao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
}


