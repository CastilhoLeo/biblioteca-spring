package br.com.leonardo.bibliotecaspring.builders;

import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;

import java.time.LocalDate;

public class LivroDtoBuilder {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private int edicao;
    private SituacaoLivro situacaoLivro;
    private int estoqueInicial;

    private LivroDtoBuilder(){
    }

    public static void inicializarDadosPadroes(LivroDtoBuilder builder){
        builder.id = 1L;
        builder.titulo = "Harry Potter";
        builder.autor = "J.K. Rowling";
        builder.dataPublicacao = LocalDate.of(1997, 6, 26);
        builder.edicao = 1;
        builder.estoqueInicial = 10;
        builder.situacaoLivro = SituacaoLivro.DISPONIVEL;
    }

    public static LivroDtoBuilder umLivro(){
        LivroDtoBuilder builder = new LivroDtoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public LivroDTO agora(){
        return new LivroDTO(id,titulo,autor,dataPublicacao,edicao, situacaoLivro, estoqueInicial);
    }


    public Long getId() {
        return id;
    }

    public LivroDtoBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public LivroDtoBuilder comTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getAutor() {
        return autor;
    }

    public LivroDtoBuilder comAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public LivroDtoBuilder comDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
        return this;
    }

    public int getEdicao() {
        return edicao;
    }

    public LivroDtoBuilder comEdicao(int edicao) {
        this.edicao = edicao;
        return this;
    }

    public SituacaoLivro getSituacaoLivro() {
        return situacaoLivro;
    }

    public void comSituacaoLivro(SituacaoLivro situacaoLivro) {
        this.situacaoLivro = situacaoLivro;
    }

    public int getEstoqueInicial() {
        return estoqueInicial;
    }

    public void comEstoqueInicial(int estoqueInicial) {
        this.estoqueInicial = estoqueInicial;
    }
}
