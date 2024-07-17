package br.com.leonardo.bibliotecaspring.service;


import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.builders.LivroDtoBuilder;
import br.com.leonardo.bibliotecaspring.converter.LivroConverter;
import br.com.leonardo.bibliotecaspring.dto.CadastroLivroRequest;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.exception.LivroNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.EstoqueRepository;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @Mock
    private EstoqueService estoqueService;

    @Mock
    private LivroConverter livroConverter;

    @InjectMocks
    private LivroService service;


    @Test
    public void pesquisarPeloId_DeveRetornarUmLivro(){
        Livro livro = LivroBuilder.umLivro().agora();
        LivroDTO livroDTO = LivroDtoBuilder.umLivro().agora();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(livro));
        Mockito.when(livroConverter.toDto(any(Livro.class))).thenReturn(livroDTO);

        LivroDTO resultado = service.pesquisarPeloId(1L);

        Assertions.assertEquals(resultado.getClass(), LivroDTO.class);
        Assertions.assertEquals(resultado.getTitulo(), "Harry Potter");
        Assertions.assertNotNull(resultado);
        Mockito.verify(repository, times(1)).findById(1L);
        Mockito.verify(livroConverter, times(1)).toDto(livro);
    }

    @Test
    public void pesquisarPeloId_DeveRetornarUmaExceptionPeloIdNaoLocalizado() {

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());

        LivroNaoEncontradoException exception = Assertions.assertThrows(LivroNaoEncontradoException.class, () -> service.pesquisarPeloId(1L));

        Assertions.assertEquals(exception.getMessage(), "Livro não encontrado!");
    }

    @Test
    public void cadastrarLivro_DeveRetornarUmLivroSalvo(){
        Livro livro = LivroBuilder.umLivro().agora();
        LivroDTO livroDto = LivroDtoBuilder.umLivro().agora();
        Estoque estoque = new Estoque();
        CadastroLivroRequest request = new CadastroLivroRequest(livroDto, 1);

        Mockito.when(repository.save(any(Livro.class))).thenReturn(livro);
        Mockito.when(livroConverter.toDto(any(Livro.class))).thenReturn(livroDto);
        Mockito.when(livroConverter.toEntity(any(LivroDTO.class))).thenReturn(livro);
        Mockito.when(estoqueService.inserirEstoqueInicial(1L, 1)).thenReturn(estoque);

        LivroDTO resultado = service.cadastrarLivro(request);

        Assertions.assertEquals(resultado.getClass(), LivroDTO.class);
        Mockito.verify(repository, times(1)).save(any(Livro.class));
        Assertions.assertEquals(resultado, livroDto);
        Mockito.verify(estoqueService, times(1)).inserirEstoqueInicial(1L, 1);
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
        Mockito.when(livroConverter.toDto(any(Livro.class))).thenReturn(livroDto);

        LivroDTO livroAlterado = service.editarLivro(1L, livroDto);

        Mockito.verify(repository, times(1)).save(livro);
        Mockito.verify(repository, times(1)).findById(1L);

        Assertions.assertNotNull(livroAlterado);
    }

    @Test
    public void pesquisaDinamica_DeveRetornarTodosOsValoresAtravésDoFindAll(){

        List<Livro> lista = new ArrayList<>();

        Livro livro1 = LivroBuilder.umLivro().agora();
        Livro livro2 = LivroBuilder.umLivro().comId(2L).comTitulo("Teste 1").agora();
        Livro livro3 = LivroBuilder.umLivro().comId(3L).comTitulo("Teste 2").agora();

        lista.add(livro1);
        lista.add(livro2);
        lista.add(livro3);

        LivroDTO livroDto1 = LivroDtoBuilder.umLivro().agora();
        LivroDTO livroDto2 = LivroDtoBuilder.umLivro().comId(2L).comTitulo("Teste 1").agora();
        LivroDTO livroDto3 = LivroDtoBuilder.umLivro().comId(3l).comTitulo("Teste 2").agora();

        Page<Livro> pageLivro = new PageImpl<>(lista);

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(pageLivro);
        Mockito.when(livroConverter.toDto(livro1)).thenReturn(livroDto1);
        Mockito.when(livroConverter.toDto(livro2)).thenReturn(livroDto2);
        Mockito.when(livroConverter.toDto(livro3)).thenReturn(livroDto3);

        Page<LivroDTO> pageRetornado = service.pesquisDinamica("","",Pageable.ofSize(5));

        Assertions.assertEquals(pageRetornado.getSize(), 3);
        Assertions.assertNotNull(pageRetornado);
        Mockito.verify(repository, times(1)).findAll(Pageable.ofSize(5));
        Assertions.assertEquals(pageRetornado.getContent().get(0).getTitulo(), "Harry Potter");
        Assertions.assertEquals(pageRetornado.getContent().get(1).getTitulo(), "Teste 1");
        Assertions.assertEquals(pageRetornado.getContent().get(2).getTitulo(), "Teste 2");

    }

    @Test
    public void pesquisaDinamica_DeveRetornarApenasOLivroPesquisado(){
        List<Livro> lista = new ArrayList<>();
        Livro livro = LivroBuilder.umLivro().agora();
        lista.add(livro);

        LivroDTO livroDto = LivroDtoBuilder.umLivro().agora();

        Page<Livro> pageLivro = new PageImpl<>(lista);

        Mockito.when(repository.pesquisaDinamica(anyString(), anyString(), any(Pageable.class))).thenReturn(pageLivro);
        Mockito.when(livroConverter.toDto(livro)).thenReturn(livroDto);

        Page<LivroDTO> pageRetornado = service.pesquisDinamica("","Senhor",Pageable.ofSize(5));

        Assertions.assertEquals(pageRetornado.getSize(), 1);
        Assertions.assertNotNull(pageRetornado);
        Mockito.verify(repository, times(1)).pesquisaDinamica("","Senhor",Pageable.ofSize(5));
        Assertions.assertEquals(pageRetornado.getContent().get(0).getId(), 1L);
    }

}
