package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.converter.EntradaConverter;
import br.com.leonardo.bibliotecaspring.dto.EntradaDTO;
import br.com.leonardo.bibliotecaspring.dto.SalvarEntradaRequest;
import br.com.leonardo.bibliotecaspring.entity.Entrada;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.repository.EntradaRepository;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EntradaServiceTest {

    @Mock
    private EntradaRepository entradaRepository;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private EntradaConverter entradaConverter;

    @Mock
    private EstoqueService estoqueService;

    @InjectMocks
    private EntradaService entradaService;

    @Test
    public void salvarEntrada_DeveCadastrarUmaEntrada(){

        Livro livro = LivroBuilder.umLivro().agora();
        EntradaDTO entradaDTO = new EntradaDTO();
        Entrada entrada = new Entrada();


        SalvarEntradaRequest salvarEntradaRequest = new SalvarEntradaRequest();
        salvarEntradaRequest.setIdLivro(livro.getId());
        salvarEntradaRequest.setData(LocalDate.of(2024,10,9));
        salvarEntradaRequest.setQuantidade(1);


        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(entradaRepository.save(any(Entrada.class))).thenReturn(entrada);
        Mockito.when(entradaConverter.toDto(any(Entrada.class))).thenReturn(entradaDTO);

        EntradaDTO res = entradaService.salvarEntrada(salvarEntradaRequest);

        Assertions.assertEquals(res.getClass(), EntradaDTO.class);
        Assertions.assertNotNull(res);
        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(entradaRepository, Mockito.times(1)).save(any(Entrada.class));
        Mockito.verify(entradaConverter, Mockito.times(1)).toDto(any(Entrada.class));

    }
}
