package br.com.leonardo.bibliotecaspring.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LivroTest {

    @Test
    public void deveInstanciarLivro(){
        Livro livro = new Livro();
               livro.setId(1L);
               livro.setTitulo("Harry Potter");
               livro.setAutor("J.K.Rowling");
                livro.setDataPublicacao(LocalDate.of(1997, 06, 26));
               livro.setEdicao(1);

        Assertions.assertNotNull(livro);
        Assertions.assertEquals(Livro.class, livro.getClass());
        Assertions.assertEquals(livro.getId(), 1L);
        Assertions.assertEquals(livro.getTitulo(), "Harry Potter");
        Assertions.assertEquals(livro.getAutor(), "J.K.Rowling");
        Assertions.assertEquals(livro.getDataPublicacao(), LocalDate.of(1997,06,26));
        Assertions.assertEquals(livro.getEdicao(), 1);
    }
}
