package br.com.leonardo.bibliotecaspring.enums;

public enum SituacaoLivro {

    SEM_ESTOQUE ("Sem estoque"),
    DISPONIVEL("Disponivel");

    private String situacaoLivro;

    SituacaoLivro(String situacaoLivro){
        this.situacaoLivro = situacaoLivro;
    }

    public String getSituacaoLivro() {
        return situacaoLivro;
    }
}
