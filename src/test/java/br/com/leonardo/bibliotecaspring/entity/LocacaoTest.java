package br.com.leonardo.bibliotecaspring.entity;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.enums.PrazoLocacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocacaoTest {

    @Test
    public void deveInstanciarUmaLocacao(){
        Livro livro = LivroBuilder.umLivro().agora();
        Cliente cliente = ClienteBuilder.cliente().build();
        Locacao locacao = new Locacao();

        locacao.setCliente(cliente);
        locacao.setLivro(livro);
        locacao.setPrazoLocacao(PrazoLocacao.DIAS_15);
        locacao.setDataSaida(LocalDate.of(2024,6,15));
        locacao.setDataPrevistaDevolucao(LocalDate.of(2024,6,30));
        locacao.setDataEfetivaDevolucao(LocalDate.of(2024,6,30));

        Assertions.assertEquals(locacao.getLivro(), livro);
        Assertions.assertEquals(locacao.getCliente(), cliente);
        Assertions.assertEquals(locacao.getPrazoLocacao(), PrazoLocacao.DIAS_15);
        Assertions.assertEquals(locacao.getDataSaida(), LocalDate.of(2024,6,15));
        Assertions.assertEquals(locacao.getDataPrevistaDevolucao(), LocalDate.of(2024,6,30));
        Assertions.assertEquals(locacao.getDataEfetivaDevolucao(), LocalDate.of(2024,6,30));
    }
}
