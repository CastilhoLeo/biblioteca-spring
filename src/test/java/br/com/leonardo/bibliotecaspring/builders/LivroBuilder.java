package br.com.leonardo.bibliotecaspring.builders;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Livro;

import java.time.LocalDate;

public class LivroBuilder {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private int edicao;

    private LivroBuilder(){
    }

    public static void inicializarDadosPadroes(LivroBuilder builder){
        builder.id = 1L;
        builder.titulo = "Harry Potter";
        builder.autor = "J.K. Rowling";
        builder.dataPublicacao = LocalDate.of(1997, 6, 26);
        builder.edicao = 1;
    }

    public static LivroBuilder umLivro(){
        LivroBuilder builder = new LivroBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public Livro agora(){
        return new Livro(id,titulo,autor,dataPublicacao,edicao);
    }


    public Long getId() {
        return id;
    }

    public LivroBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public LivroBuilder comTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getAutor() {
        return autor;
    }

    public LivroBuilder comAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public LivroBuilder comDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
        return this;
    }

    public int getEdicao() {
        return edicao;
    }

    public LivroBuilder comEdicao(int edicao) {
        this.edicao = edicao;
        return this;
    }


}
