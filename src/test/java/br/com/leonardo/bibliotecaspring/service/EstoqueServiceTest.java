package br.com.leonardo.bibliotecaspring.service;


import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import br.com.leonardo.bibliotecaspring.exception.EstoqueJaInseridoException;
import br.com.leonardo.bibliotecaspring.exception.EstoqueNegativoException;
import br.com.leonardo.bibliotecaspring.exception.LivroNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.repository.EstoqueRepository;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class EstoqueServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private EstoqueRepository estoqueRepository;


    @InjectMocks
    private EstoqueService estoqueService;



//    @Test
//    public void inserirEstoqueInicial_deveInserirUmEstoqueInicial(){
//
//        Livro livro = LivroBuilder.umLivro().agora();
//
//        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
//        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.of(livro.getEstoque()));
//
//        estoqueService.inserirEstoqueInicial(1L, 1);
//
//        Assertions.assertEquals(livro.getEstoque().getEstoqueInicial(), 1);
//        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
//        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);
//
//    }
//
//    @Test
//    public void inserirEstoqueInicial_deveRetornarExceptionLivroNaoEcontrado(){
//        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        LivroNaoEncontradoException ex = Assertions.assertThrows(LivroNaoEncontradoException.class,
//                ()->estoqueService.inserirEstoqueInicial(1L, 1));
//
//        Assertions.assertEquals(ex.getMessage(), "Livro não encontrado!");
//
//    }

//    @Test
//    public void inserirEstoqueInicial_deveRetornarExceptionEstoqueNegativo(){
//
//        Livro livro = LivroBuilder.umLivro().agora();
//
//        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
//
//        EstoqueNegativoException ex = Assertions.assertThrows(EstoqueNegativoException.class,
//                ()-> estoqueService.inserirEstoqueInicial(1L, -1));
//
//        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
//        Assertions.assertEquals(ex.getMessage(), "Estoque inicial não pode ser negativo!");
//
//    }

//    @Test
//    public void inserirEstoqueInicial_deveRetornarExceptionEstoqueJaInserido(){
//
//        Livro livro = LivroBuilder.umLivro().agora();
//        livro.getEstoque().setEstoqueInicial(1);
//
//        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
//
//        EstoqueJaInseridoException ex = Assertions.assertThrows(EstoqueJaInseridoException.class,
//                ()-> estoqueService.inserirEstoqueInicial(1L, 1));
//
//        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
//        Assertions.assertEquals(ex.getMessage(), "Estoque inicial já cadastrado!");
//
//    }

    @Test
    public void saidaEstoque_deveDarSaidaNoEstoqueAtual(){
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque(1L,1,null,livro);
        livro.setEstoque(estoque);

        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.of(estoque));

        estoqueService.saidaEsoqueLocacao(livro);

        Assertions.assertEquals(estoque.getEstoqueAtual(), 0);
        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verificarDisponibilidade_DeveFicarEstoqueDisponivel(){
        Livro livro = LivroBuilder.umLivro().agora();
        livro.getEstoque().setId(1L);
        livro.getEstoque().setEstoqueAtual(1);


        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.of(livro.getEstoque()));

        estoqueService.verificarDisponibilidade(livro.getEstoque().getId());

        Assertions.assertEquals(livro.getEstoque().getSituacaoLivro(), SituacaoLivro.DISPONIVEL);
    }

    @Test
    public void verificarDisponibilidade_DeveFicarSemEstoque(){
        Livro livro = LivroBuilder.umLivro().agora();
        livro.getEstoque().setId(1L);
        livro.getEstoque().setEstoqueAtual(0);


        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.of(livro.getEstoque()));

        estoqueService.verificarDisponibilidade(livro.getEstoque().getId());

        Assertions.assertEquals(livro.getEstoque().getSituacaoLivro(), SituacaoLivro.SEM_ESTOQUE);
    }


    @Test
    public void verificarDisponibilidade_DeveRetornarExceptionLivroNaoEncontrado(){

        Livro livro = LivroBuilder.umLivro().agora();
        livro.getEstoque().setId(1L);

        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.empty());

        LivroNaoEncontradoException ex = Assertions.assertThrows(LivroNaoEncontradoException.class,()-> estoqueService.verificarDisponibilidade(livro.getEstoque().getId()));

        Assertions.assertEquals(ex.getMessage(), "Livro não encontrado!");

    }

    @Test
    public void retornoEstoque_DeveDevolverLivroAoEstoque(){
        Livro livro = LivroBuilder.umLivro().agora();
        livro.getEstoque().setId(1L);
        livro.getEstoque().setEstoqueAtual(1);

        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.of(livro.getEstoque()));

        estoqueService.retornoEstoque(livro);

        Assertions.assertEquals(livro.getEstoque().getEstoqueAtual(), 2);
        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void retornoEstoque_DeveRetornarLivroNaoEncontradoException(){
        Livro livro = LivroBuilder.umLivro().agora();
        livro.getEstoque().setId(1L);

        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.empty());

        LivroNaoEncontradoException ex = Assertions.assertThrows(LivroNaoEncontradoException.class,()-> estoqueService.retornoEstoque(livro));

        Assertions.assertEquals(ex.getMessage(), "Livro não encontrado!");

    }

}
