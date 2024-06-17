package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.LocacaoConverter;
import br.com.leonardo.bibliotecaspring.dto.LocacaoDTO;
import br.com.leonardo.bibliotecaspring.dto.SalvarLocacaoRequest;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.exception.*;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import br.com.leonardo.bibliotecaspring.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LocacaoConverter locacaoConverter;




    @Transactional
    public LocacaoDTO salvarLocacao(SalvarLocacaoRequest locacaoRequest){
        Locacao locacao = new Locacao();

        locacao.setPrazoLocacao(locacaoRequest.getPrazoLocacao());

        locacao.setLivro(livroRepository.findById(locacaoRequest.getIdLivro()).orElseThrow(()-> new LivroNaoEncontradoException()));
        locacao.setCliente(clienteRepository.findById(locacaoRequest.getIdCliente()).orElseThrow(()-> new ClienteNaoEncontradoException()));
        locacao.setDataSaida(LocalDate.now());


        switch (locacaoRequest.getPrazoLocacao()){
            case DIAS_15:
                locacao.setDataPrevistaDevolucao(LocalDate.now().plusDays(15));
                break;

            case DIAS_30:
                locacao.setDataPrevistaDevolucao(LocalDate.now().plusDays(30));
                break;

            case DIAS_45:
                locacao.setDataPrevistaDevolucao(LocalDate.now().plusDays(45));
                break;

            default:
                throw new PrazoDeLocacaoInvalidoException();
        }

        if(locacao.getLivro().getEstoque().getEstoqueAtual()>0) {
            estoqueService.saidaEstoque(locacao.getLivro());
            estoqueService.verificarDisponibilidade(locacao.getLivro().getEstoque().getId());
            return locacaoConverter.toDto(locacaoRepository.save(locacao));
        }
        else{
            throw new EstoqueInsuficienteException();
        }

    }

    @Transactional
    public LocacaoDTO devolverLocacao(Long id){
        Locacao locacao = locacaoRepository.findById(id).orElseThrow(()->new LocacaoNaoLocalizadaException());

        if (locacao.getDataEfetivaDevolucao() == null) {
            estoqueService.retornoEstoque(locacao.getLivro());
            locacao.setDataEfetivaDevolucao(LocalDate.now());
            estoqueService.verificarDisponibilidade(id);
        }
        else{
            throw new LivroJaDevolvidoException();
        }

        return locacaoConverter.toDto(locacao);
    }
}
