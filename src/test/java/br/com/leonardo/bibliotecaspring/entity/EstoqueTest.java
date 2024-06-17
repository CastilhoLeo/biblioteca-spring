package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EstoqueTest {

    @Test
    public void deveInstanciarUmEstoque(){
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque();
        estoque.setEstoqueInicial(1);
        estoque.setLivro(livro);
        estoque.setEstoqueAtual(1);
        estoque.setSituacaoLivro(SituacaoLivro.DISPONIVEL);

        Assertions.assertEquals(estoque.getEstoqueAtual(), 1);
        Assertions.assertEquals(estoque.getEstoqueInicial(), 1);
        Assertions.assertEquals(estoque.getLivro(), livro);
        Assertions.assertEquals(estoque.getSituacaoLivro(), SituacaoLivro.DISPONIVEL);
    }
}
