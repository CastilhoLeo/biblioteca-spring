package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.builders.LivroDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.EstoqueDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootTest
public class LivroConverterTest {

    @Autowired
    private LivroConverter livroConverter;

    @Test
    public void deveConverterUmLivroEntityParaDto(){
        Livro livro = LivroBuilder.umLivro().agora();
        LivroDTO livroDto = livroConverter.toDto(livro);

        Assertions.assertNotNull(livroDto);
        Assertions.assertEquals(livroDto.getClass(), LivroDTO.class);
        Assertions.assertEquals(livroDto.getId(), 1L);
        Assertions.assertEquals(livroDto.getTitulo(), "Harry Potter");
        Assertions.assertEquals(livroDto.getAutor(), "J.K. Rowling");
        Assertions.assertEquals(livroDto.getEdicao(), 1);
        Assertions.assertEquals(livroDto.getDataPublicacao(), LocalDate.of(1997, 6, 26));
        Assertions.assertEquals(livroDto.getEstoqueDto(), new EstoqueDTO());
        Assertions.assertEquals(livroDto.getEstoqueDto().getClass(), EstoqueDTO.class);

    }

    @Test
    public void deveConverterUmLivroDtoParaEntity(){
        LivroDTO livroDto = LivroDtoBuilder.umLivro().agora();
        Livro livro = livroConverter.toEntity(livroDto);

        System.out.println(livro);

        Assertions.assertNotNull(livro);
        Assertions.assertEquals(livro.getClass(), Livro.class);
        Assertions.assertEquals(livro.getId(), 1L);
        Assertions.assertEquals(livro.getTitulo(), "Harry Potter");
        Assertions.assertEquals(livro.getAutor(), "J.K. Rowling");
        Assertions.assertEquals(livro.getEdicao(), 1);
        Assertions.assertEquals(livro.getDataPublicacao(), LocalDate.of(1997, 6, 26));
        Assertions.assertEquals(livro.getEstoque(),new Estoque());
        Assertions.assertEquals(livro.getEstoque().getClass(),Estoque.class);
        Assertions.assertEquals(livro.getLocacoes(), new HashSet<Locacao>());

    }
}
