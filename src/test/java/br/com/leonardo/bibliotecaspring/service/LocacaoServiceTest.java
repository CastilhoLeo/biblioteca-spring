package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.LivroBuilder;
import br.com.leonardo.bibliotecaspring.converter.LocacaoConverter;
import br.com.leonardo.bibliotecaspring.dto.LocacaoDTO;
import br.com.leonardo.bibliotecaspring.dto.SalvarLocacaoRequest;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.enums.PrazoLocacao;
import br.com.leonardo.bibliotecaspring.exception.*;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import br.com.leonardo.bibliotecaspring.repository.LocacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class LocacaoServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private LocacaoRepository locacaoRepository;

    @Mock
    private EstoqueService estoqueService;

    @Mock
    private LocacaoConverter locacaoConverter;

    @InjectMocks
    private LocacaoService locacaoService;

    @Test
    public void salvarLocacao_DeveRetornarUmaLocacaoSalvaComPrazo15Dias() {

        SalvarLocacaoRequest request = new SalvarLocacaoRequest(1L, 1L, PrazoLocacao.DIAS_15);
        Locacao locacao = new Locacao();
        Cliente cliente = ClienteBuilder.cliente().build();
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque();
        estoque.setId(1L);
        livro.setEstoque(estoque);
        livro.getEstoque().setEstoqueAtual(1);
        locacao.setLivro(livro);
        locacao.setCliente(cliente);
        LocacaoDTO locacaoDTO = new LocacaoDTO();

        ArgumentCaptor<Locacao> locacaoCaptor = ArgumentCaptor.forClass(Locacao.class);

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        Mockito.when(locacaoConverter.toDto(any(Locacao.class))).thenReturn(locacaoDTO);
        Mockito.when(locacaoRepository.save(any(Locacao.class))).thenReturn(locacao);

        locacaoService.salvarLocacao(request);

        Mockito.verify(locacaoRepository).save(locacaoCaptor.capture());
        Locacao locacaoSalva = locacaoCaptor.getValue();

        Assertions.assertEquals(locacaoSalva.getDataSaida(), LocalDate.now());
        Assertions.assertEquals(locacaoSalva.getLivro(), livro);
        Assertions.assertEquals(locacaoSalva.getCliente(), cliente);
        Assertions.assertEquals(locacaoSalva.getPrazoLocacao(), PrazoLocacao.DIAS_15);

        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(estoqueService, Mockito.times(1)).saidaEsoqueLocacao(locacao.getLivro());
        Mockito.verify(estoqueService, Mockito.times(1)).verificarDisponibilidade(1L);
        Mockito.verify(locacaoConverter, Mockito.times(1)).toDto(locacao);
    }

    @Test
    public void salvarLocacao_DeveRetornarUmaLocacaoSalvaComPrazo30Dias() {

        SalvarLocacaoRequest request = new SalvarLocacaoRequest(1L, 1L, PrazoLocacao.DIAS_30);
        Locacao locacao = new Locacao();
        Cliente cliente = ClienteBuilder.cliente().build();
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque();
        estoque.setId(1L);
        livro.setEstoque(estoque);
        livro.getEstoque().setEstoqueAtual(1);
        locacao.setLivro(livro);
        locacao.setCliente(cliente);
        LocacaoDTO locacaoDTO = new LocacaoDTO();

        ArgumentCaptor<Locacao> locacaoCaptor = ArgumentCaptor.forClass(Locacao.class);

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        Mockito.when(locacaoConverter.toDto(any(Locacao.class))).thenReturn(locacaoDTO);
        Mockito.when(locacaoRepository.save(any(Locacao.class))).thenReturn(locacao);

        locacaoService.salvarLocacao(request);

        Mockito.verify(locacaoRepository).save(locacaoCaptor.capture());
        Locacao locacaoSalva = locacaoCaptor.getValue();

        Assertions.assertEquals(locacaoSalva.getDataSaida(), LocalDate.now());
        Assertions.assertEquals(locacaoSalva.getLivro(), livro);
        Assertions.assertEquals(locacaoSalva.getCliente(), cliente);
        Assertions.assertEquals(locacaoSalva.getPrazoLocacao(), PrazoLocacao.DIAS_30);

        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(estoqueService, Mockito.times(1)).saidaEsoqueLocacao(locacao.getLivro());
        Mockito.verify(estoqueService, Mockito.times(1)).verificarDisponibilidade(1L);
        Mockito.verify(locacaoConverter, Mockito.times(1)).toDto(locacao);
    }


    @Test
    public void salvarLocacao_DeveRetornarUmaLocacaoSalvaComPrazo45Dias() {

        SalvarLocacaoRequest request = new SalvarLocacaoRequest(1L, 1L, PrazoLocacao.DIAS_45);
        Locacao locacao = new Locacao();
        Cliente cliente = ClienteBuilder.cliente().build();
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque();
        estoque.setId(1L);
        livro.setEstoque(estoque);
        livro.getEstoque().setEstoqueAtual(1);
        locacao.setLivro(livro);
        locacao.setCliente(cliente);
        LocacaoDTO locacaoDTO = new LocacaoDTO();

        ArgumentCaptor<Locacao> locacaoCaptor = ArgumentCaptor.forClass(Locacao.class);

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        Mockito.when(locacaoConverter.toDto(any(Locacao.class))).thenReturn(locacaoDTO);
        Mockito.when(locacaoRepository.save(any(Locacao.class))).thenReturn(locacao);

        locacaoService.salvarLocacao(request);

        Mockito.verify(locacaoRepository).save(locacaoCaptor.capture());
        Locacao locacaoSalva = locacaoCaptor.getValue();

        Assertions.assertEquals(locacaoSalva.getDataSaida(), LocalDate.now());
        Assertions.assertEquals(locacaoSalva.getLivro(), livro);
        Assertions.assertEquals(locacaoSalva.getCliente(), cliente);
        Assertions.assertEquals(locacaoSalva.getPrazoLocacao(), PrazoLocacao.DIAS_45);

        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(estoqueService, Mockito.times(1)).saidaEsoqueLocacao(locacao.getLivro());
        Mockito.verify(estoqueService, Mockito.times(1)).verificarDisponibilidade(1L);
        Mockito.verify(locacaoConverter, Mockito.times(1)).toDto(locacao);
    }

    @Test
    public void salvarLocacao_DeveRetornarExceptionEstoqueInsuficiente() {

        SalvarLocacaoRequest request = new SalvarLocacaoRequest(1L, 1L, PrazoLocacao.DIAS_45);
        Locacao locacao = new Locacao();
        Cliente cliente = ClienteBuilder.cliente().build();
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque();
        estoque.setId(1L);
        livro.setEstoque(estoque);
        livro.getEstoque().setEstoqueAtual(-1);
        locacao.setLivro(livro);
        locacao.setCliente(cliente);

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Assertions.assertThrows(EstoqueInsuficienteException.class, () -> locacaoService.salvarLocacao(request));

        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    public void salvarLocacao_DeveRetornarExceptionLivroNaoEncontrado() {

        SalvarLocacaoRequest request = new SalvarLocacaoRequest(1L, 1L, PrazoLocacao.DIAS_45);
        Locacao locacao = new Locacao();
        Livro livro = LivroBuilder.umLivro().agora();

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(LivroNaoEncontradoException.class, () -> locacaoService.salvarLocacao(request));

    }

    @Test
    public void salvarLocacao_DeveRetornarExceptionClienteNaoEncontrado() {

        SalvarLocacaoRequest request = new SalvarLocacaoRequest(1L, 1L, PrazoLocacao.DIAS_45);
        Locacao locacao = new Locacao();
        Cliente cliente = ClienteBuilder.cliente().build();
        Livro livro = LivroBuilder.umLivro().agora();
        Estoque estoque = new Estoque();
        estoque.setId(1L);
        livro.setEstoque(estoque);
        livro.getEstoque().setEstoqueAtual(-1);
        locacao.setLivro(livro);
        locacao.setCliente(cliente);

        Mockito.when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ClienteNaoEncontradoException.class, () -> locacaoService.salvarLocacao(request));
        Mockito.verify(livroRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    public void devolverLocacao_deveInserirDataEfetivaDevolução() {

        Locacao locacao = new Locacao();
        LocacaoDTO locacaoDTO = new LocacaoDTO();
        Livro livro = LivroBuilder.umLivro().agora();
        locacao.setLivro(livro);

        ArgumentCaptor<Locacao> locacaoCaptor = ArgumentCaptor.forClass(Locacao.class);
        Mockito.when(locacaoRepository.findById(anyLong())).thenReturn(Optional.of(locacao));
        Mockito.when(locacaoConverter.toDto(locacao)).thenReturn(locacaoDTO);

        locacaoService.devolverLocacao(1L);

        Mockito.verify(locacaoConverter).toDto(locacaoCaptor.capture());
        Locacao locacaoSalva = locacaoCaptor.getValue();

        Assertions.assertEquals(locacaoSalva.getDataEfetivaDevolucao(), LocalDate.now());
        Mockito.verify(estoqueService, Mockito.times(1)).retornoEstoque(locacao.getLivro());
        Mockito.verify(estoqueService, Mockito.times(1)).verificarDisponibilidade(locacao.getLivro().getEstoque().getId());
        Mockito.verify(locacaoConverter, Mockito.times(1)).toDto(locacao);

    }


    @Test
    public void devolverLocacao_deveRetornarExceptionLivroJaDevolvido() {

        Locacao locacao = new Locacao();
        locacao.setDataEfetivaDevolucao(LocalDate.now());

        ArgumentCaptor<Locacao> locacaoCaptor = ArgumentCaptor.forClass(Locacao.class);
        Mockito.when(locacaoRepository.findById(anyLong())).thenReturn(Optional.of(locacao));

        Assertions.assertThrows(LivroJaDevolvidoException.class, ()-> locacaoService.devolverLocacao(1L));

    }

    @Test
    public void devolverLocacao_deveRetornarLocacaoNaoLocalizadaException() {

        Mockito.when(locacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(LocacaoNaoLocalizadaException.class, ()-> locacaoService.devolverLocacao(1L));

    }
}
