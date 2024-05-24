package br.com.leonardo.bibliotecaspring.service;


import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.builders.LivroDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroService service;



    @Test
    public void pesquisarPeloId_DeveRetornarUmLivro(){
        Livro livro = LivroBuilder.umLivro().agora();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(livro));

        LivroDTO resultado = service.pesquisarPeloId(1L);

        Assertions.assertEquals(resultado.getClass(), LivroDTO.class);
        Assertions.assertEquals(resultado.getTitulo(), "Harry Potter");
        Assertions.assertNotNull(resultado);
    }

    @Test
    public void pesquisarPeloId_DeveRetornarUmaExceptionPeloIdNaoLocalizado() {

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());

        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.pesquisarPeloId(1L));

        Assertions.assertEquals(exception.getMessage(), "Livro não encontrado");
    }

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

    @Test
    public void deletarLivro_DeveDeletarLivroPeloIdInformado(){

        Livro livro = LivroBuilder.umLivro().agora();
        service.deletarLivro(livro.getId());

        Mockito.verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void editarLivro_DeveRetornarLivroAlterado(){
        Livro livro = LivroBuilder.umLivro().agora();
        LivroDTO livroDto = LivroDtoBuilder.umLivro().comTitulo("Teste").agora();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(livro));
        Mockito.when(repository.save(any())).thenReturn(livro);

        Livro livroAlterado = service.editarLivro(1L, livroDto);

        Mockito.verify(repository, times(1)).save(livro);
        Mockito.verify(repository, times(1)).findById(1L);

        Assertions.assertEquals(livroAlterado.getTitulo(), "Teste");
        Assertions.assertNotNull(livroAlterado);
    }

    @Test
    public void pesquisaDinamica_DeveRetornarTodosOsValoresAtravésDoFindAll(){
        List<Livro> lista = new ArrayList<>();
        lista.add(LivroBuilder.umLivro().agora());
        lista.add(LivroBuilder.umLivro().comTitulo("Teste 1").agora());
        lista.add(LivroBuilder.umLivro().comTitulo("Teste 2").agora());

        Page<Livro> pageLivro = new PageImpl<>(lista);

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(pageLivro);

        Page<LivroDTO> pageRetornado = service.pesquisDinamica("","",Pageable.ofSize(5));

        Assertions.assertEquals(pageRetornado.getSize(), 3);
        Assertions.assertNotNull(pageRetornado);
        Mockito.verify(repository, times(1)).findAll(Pageable.ofSize(5));
    }

    @Test
    public void pesquisaDinamica_DeveRetornarApenasOLivroPesquisado(){
        List<Livro> lista = new ArrayList<>();
        lista.add(LivroBuilder.umLivro().agora());
        Page<Livro> pageLivro = new PageImpl<>(lista);

        Mockito.when(repository.pesquisaDinamica(anyString(), anyString(), any(Pageable.class))).thenReturn(pageLivro);

        Page<LivroDTO> pageRetornado = service.pesquisDinamica("","Senhor",Pageable.ofSize(5));

        Assertions.assertEquals(pageRetornado.getSize(), 1);
        Assertions.assertNotNull(pageRetornado);
        Mockito.verify(repository, times(1)).pesquisaDinamica("","Senhor",Pageable.ofSize(5));
    }

}
