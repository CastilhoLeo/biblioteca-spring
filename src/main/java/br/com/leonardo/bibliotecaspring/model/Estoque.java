package br.com.leonardo.bibliotecaspring.model;

public class Estoque {

    private Livro livro;
    private int quantidade;

    public Estoque(){
    }

    public Estoque(Livro livro, int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
    }


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
