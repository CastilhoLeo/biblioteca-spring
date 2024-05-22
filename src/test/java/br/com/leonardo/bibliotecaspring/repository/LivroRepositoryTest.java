package br.com.leonardo.bibliotecaspring.repository;


import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    private Pageable pageable;

    @BeforeAll
    public static void setup(@Autowired LivroRepository livroRepository){
        livroRepository.saveAll(List.of(
                LivroBuilder.umLivro().agora(),
                LivroBuilder.umLivro().comId(2L).comTitulo("Senhor dos Aneis").comAutor("J.R.R.Tolkien").agora(),
                LivroBuilder.umLivro().comId(3L).comTitulo("Eragon").comAutor("Christopher Paolini").agora()
        ));
    }

    @Test
    @Order(1)
    @Transactional
    public void pesquisaDinamica_DeveRetornarUmPageComTodosOsLivros(){

        Livro livro1 = LivroBuilder.umLivro().agora();
        Livro livro2 = LivroBuilder.umLivro().comId(2L).comTitulo("Senhor dos Aneis").comAutor("J.R.R.Tolkien").agora();
        Livro livro3 = LivroBuilder.umLivro().comId(3L).comTitulo("Eragon").comAutor("Christopher Paolini").agora();

        Page<Livro> pageLivro = livroRepository.pesquisaDinamica("", "", pageable);

        Assertions.assertEquals(3, pageLivro.getTotalElements());
        Assertions.assertTrue(pageLivro.getContent().get(0).equals(livro1));
        Assertions.assertTrue(pageLivro.getContent().get(1).equals(livro2));
        Assertions.assertTrue(pageLivro.getContent().get(2).equals(livro3));
    }


    @Test
    public void pesquisaDinamica_DeveRetornarUmLivroPeloTitulo(){

        Page<Livro> pageLivro = livroRepository.pesquisaDinamica("Harry", "", pageable);

        Assertions.assertEquals(1, pageLivro.getTotalElements());
        Assertions.assertEquals(pageLivro.getContent().get(0).getId(), 1L);
        Assertions.assertEquals(pageLivro.getContent().get(0).getTitulo(), "Harry Potter");

    }

    @Test
    public void pesquisaDinamica_DeveRetornarUmLivroPeloAutor(){

        Page<Livro> pageLivro = livroRepository.pesquisaDinamica("", "J.K", pageable);

        Assertions.assertEquals(1, pageLivro.getTotalElements());
        Assertions.assertEquals(pageLivro.getContent().get(0).getId(), 1L);
        Assertions.assertEquals(pageLivro.getContent().get(0).getTitulo(), "Harry Potter");
    }

    @Test
    public void pesquisaDinamica_DeveRetornarUmLivroPeloTituloEAutor(){

        Page<Livro> pageLivro = livroRepository.pesquisaDinamica("Harry", "J.K", pageable);

        Assertions.assertEquals(1, pageLivro.getTotalElements());
        Assertions.assertEquals(pageLivro.getContent().get(0).getId(), 1L);
        Assertions.assertEquals(pageLivro.getContent().get(0).getTitulo(), "Harry Potter");
    }

    @Test
    public void pesquisaDinamica_DeveRetornarVazio(){

        Page<Livro> pageLivro = livroRepository.pesquisaDinamica("Teste", "", pageable);

        Assertions.assertEquals(0, pageLivro.getTotalElements());

    }
}
