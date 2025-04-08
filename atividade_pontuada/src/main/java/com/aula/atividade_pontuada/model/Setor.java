package com.aula.atividade_pontuada.model;

public enum Setor {
    ENGENHARIA("Engenharia"),
    SAUDE("Saúde"),
    JURIDICO("Jurídico");

    private String nome;

    Setor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
