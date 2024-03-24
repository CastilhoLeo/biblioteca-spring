package br.com.leonardo.bibliotecaspring.model;

import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;

public class Locacao {

    private Long id;
    private Cliente cliente;
    private Livro livro;
    private SituacaoLivro situacaoLivro;

    public Locacao(){
    }
    public Locacao(Long id, Cliente cliente, Livro livro, SituacaoLivro situacaoLivro) {
        this.id = id;
        this.cliente = cliente;
        this.livro = livro;
        this.situacaoLivro = situacaoLivro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public SituacaoLivro getSituacaoLivro() {
        return situacaoLivro;
    }

    public void setSituacaoLivro(SituacaoLivro situacaoLivro) {
        this.situacaoLivro = situacaoLivro;
    }


}
