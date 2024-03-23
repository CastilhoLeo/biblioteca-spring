package br.com.leonardo.bibliotecaspring.enums;

public enum SituacaoLivro {

    ALUGADO ("Alugado"),
    DISPONIVEL("Disponivel");

    private String situacaoLivro;

    SituacaoLivro(String situacaoLivro){
        this.situacaoLivro = situacaoLivro;
    }

    public String getSituacaoLivro() {
        return situacaoLivro;
    }
}
