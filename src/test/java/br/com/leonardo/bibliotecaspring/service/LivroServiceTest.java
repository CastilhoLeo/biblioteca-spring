package br.com.leonardo.bibliotecaspring.service;


import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.builders.LivroDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroService service;

    @Test
    public void cadastrarLivro_DeveRetornarUmLivroSalvo(){
        Livro livro = LivroBuilder.umLivro().agora();
        LivroDTO livroDto = LivroDtoBuilder.umLivro().agora();

        Mockito.when(repository.save(any(Livro.class))).thenReturn(livro);

        Livro resultado = service.cadastrarLivro(livroDto);

        Assertions.assertEquals(resultado.getClass(), Livro.class);
        Mockito.verify(repository, times(1)).save(any(Livro.class));
        Assertions.assertEquals(resultado, livro);
    }
}
