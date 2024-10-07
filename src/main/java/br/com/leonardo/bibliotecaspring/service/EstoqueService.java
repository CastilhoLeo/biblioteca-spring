package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.dto.SalvarEntradaRequest;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import br.com.leonardo.bibliotecaspring.exception.EstoqueJaInseridoException;
import br.com.leonardo.bibliotecaspring.exception.EstoqueNegativoException;
import br.com.leonardo.bibliotecaspring.exception.LivroNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.repository.EstoqueRepository;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;


    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    public Estoque entradaEstoque(Long id, int entradaEstoque){

        Livro livro = livroRepository.findById(id).orElseThrow(()-> new LivroNaoEncontradoException());

        Estoque estoque = livro.getEstoque();

        estoque.setEstoqueAtual(estoque.getEstoqueAtual() + entradaEstoque);
        verificarDisponibilidade(id);

        return estoque;

    }

    @Transactional
    public void saidaEsoqueLocacao(Livro livro){
        Estoque estoque = estoqueRepository.findById(livro.getEstoque().getId())
                .orElseThrow(()-> new LivroNaoEncontradoException());

        estoque.setEstoqueAtual(estoque.getEstoqueAtual()-1);

        estoqueRepository.save(estoque);

    }


    public void verificarDisponibilidade(Long estoqueId){
        Estoque estoque = estoqueRepository.findById(estoqueId).orElseThrow(()->new LivroNaoEncontradoException());

        if(estoque.getEstoqueAtual()>0){
            estoque.setSituacaoLivro(SituacaoLivro.DISPONIVEL);
        }else{
            estoque.setSituacaoLivro(SituacaoLivro.SEM_ESTOQUE);
        }
        estoqueRepository.save(estoque);
    }

    public void retornoEstoque(Livro livro){
        Estoque estoque = estoqueRepository.findById(livro.getEstoque().getId())
                .orElseThrow(()-> new LivroNaoEncontradoException());

        estoque.setEstoqueAtual(estoque.getEstoqueAtual()+1);

        estoqueRepository.save(estoque);
    }

}
