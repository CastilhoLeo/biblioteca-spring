package br.com.leonardo.bibliotecaspring.repository;


import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.exception.EstoqueJaInseridoException;
import br.com.leonardo.bibliotecaspring.exception.EstoqueNegativoException;
import br.com.leonardo.bibliotecaspring.exception.LivroNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.service.EstoqueService;
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



    @Test
    public void inserirEstoqueInicial_deveInserirUmEstoqueInicial(){

        Livro livro = LivroBuilder.umLivro().agora();

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.of(livro.getEstoque()));

        estoqueService.inserirEstoqueInicial(1L, 1);

        Assertions.assertEquals(livro.getEstoque().getEstoqueInicial(), 1);
        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    public void inserirEstoqueInicial_deveRetornarExceptionLivroNaoEcontrado(){
        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());

        LivroNaoEncontradoException ex = Assertions.assertThrows(LivroNaoEncontradoException.class,
                ()->estoqueService.inserirEstoqueInicial(1L, 1));

        Assertions.assertEquals(ex.getMessage(), "Livro não encontrado!");

    }

    @Test
    public void inserirEstoqueInicial_deveRetornarExceptionEstoqueNegativo(){

        Livro livro = LivroBuilder.umLivro().agora();

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));

        EstoqueNegativoException ex = Assertions.assertThrows(EstoqueNegativoException.class,
                ()-> estoqueService.inserirEstoqueInicial(1L, -1));

        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(ex.getMessage(), "Estoque inicial não pode ser negativo!");

    }

    @Test
    public void inserirEstoqueInicial_deveRetornarExceptionEstoqueJaInserido(){

        Livro livro = LivroBuilder.umLivro().agora();
        livro.getEstoque().setEstoqueInicial(1);

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));

        EstoqueJaInseridoException ex = Assertions.assertThrows(EstoqueJaInseridoException.class,
                ()-> estoqueService.inserirEstoqueInicial(1L, 1));

        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(ex.getMessage(), "Estoque inicial já cadastrado!");

    }

    @Test
    public void saidaEstoque_deveDarSaidaNoEstoqueAtual(){
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque(1L,1,1,null,livro);
        livro.setEstoque(estoque);

        Mockito.when(estoqueRepository.findById(anyLong())).thenReturn(Optional.of(estoque));

        estoqueService.saidaEstoque(livro);

        Assertions.assertEquals(estoque.getEstoqueAtual(), 0);
        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);


    }


}
