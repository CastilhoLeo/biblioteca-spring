package br.com.leonardo.bibliotecaspring.entity;

public class Estoque {

    private Long id;
    private Livro livro;
    private int quantidade;

    public Estoque(){
    }
    public Estoque(Long id, Livro livro, int quantidade) {
        this.id = id;
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
