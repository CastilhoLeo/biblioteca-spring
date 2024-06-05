package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
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

    @Transactional
    public Locacao salvarLocacao(Locacao locacao){
        locacao.setDataSaida(LocalDate.now());


        switch (locacao.getPrazoLocacao()){
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
                throw new ValidationException("Prazo de locacao invalido!");
        }

        estoqueService.saidaEstoque(locacao.getLivro());
        estoqueService.verificarDisponibilidade(locacao.getLivro().getEstoque().getId());
        return locacaoRepository.save(locacao);

    }

    @Transactional
    public Locacao devolverLocacao(Long id){
        Locacao locacao = locacaoRepository.findById(id).orElseThrow(()->new ValidationException("Locacao nao localizada"));
        estoqueService.retornoEstoque(locacao.getLivro());
        locacao.setDataEfetivaDevolucao(LocalDate.now());
        estoqueService.verificarDisponibilidade(id);

        return locacao;
    }
}
