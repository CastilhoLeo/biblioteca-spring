package br.com.leonardo.bibliotecaspring.builders;

import br.com.leonardo.bibliotecaspring.dto.EstoqueDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class LivroDtoBuilder {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private int edicao;
    private EstoqueDTO estoqueDto;
    private Set<Locacao> locacoes = new HashSet<Locacao>();

    private LivroDtoBuilder(){
    }

    public static void inicializarDadosPadroes(LivroDtoBuilder builder){
        builder.id = 1L;
        builder.titulo = "Harry Potter";
        builder.autor = "J.K. Rowling";
        builder.dataPublicacao = LocalDate.of(1997, 6, 26);
        builder.edicao = 1;
        builder.estoqueDto = new EstoqueDTO();
        builder.locacoes = new HashSet<Locacao>();
    }

    public static LivroDtoBuilder umLivro(){
        LivroDtoBuilder builder = new LivroDtoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public LivroDTO agora(){
        return new LivroDTO(id,titulo,autor,dataPublicacao,edicao, estoqueDto);
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

}
