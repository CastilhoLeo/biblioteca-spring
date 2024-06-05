package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.enums.SituacaoLivro;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private LivroService livroService;

    @Transactional
    public Estoque inserirEstoqueInicial(Long id, Estoque estoqueAtualizado){
        if(estoqueAtualizado.getEstoqueInicial()<0){
            throw new ValidationException("O estoque não pode ser negativo");
        }
        
        Estoque estoque = estoqueRepository.findById(id).orElseThrow(()->new ValidationException("Livro nao encontrado!"));
        estoque.setEstoqueInicial(estoqueAtualizado.getEstoqueInicial());
        estoque.setEstoqueAtual(estoqueAtualizado.getEstoqueInicial());

        if(estoqueAtualizado.getEstoqueInicial()>0){
            estoque.setSituacaoLivro(SituacaoLivro.DISPONIVEL);
        }else{
            estoque.setSituacaoLivro(SituacaoLivro.SEM_ESTOQUE);
        }
        return estoque;
    }

    @Transactional
    public void saidaEstoque(Livro livro){
        Estoque estoque = estoqueRepository.findById(livro.getEstoque().getId())
                .orElseThrow(()-> new ValidationException("Livro nao encontrado!"));

        estoque.setEstoqueAtual(estoque.getEstoqueAtual()-1);

        estoqueRepository.save(estoque);

    }

    public void verificarDisponibilidade(Long estoqueId){
        Estoque estoque = estoqueRepository.findById(estoqueId).orElseThrow(()->new ValidationException("Estoque nao encontrado"));

        if(estoque.getEstoqueAtual()>0){
            estoque.setSituacaoLivro(SituacaoLivro.DISPONIVEL);
        }else{
            estoque.setSituacaoLivro(SituacaoLivro.SEM_ESTOQUE);
        }
        estoqueRepository.save(estoque);
    }

    public void retornoEstoque(Livro livro){
        Estoque estoque = estoqueRepository.findById(livro.getEstoque().getId())
                .orElseThrow(()-> new ValidationException("Livro nao encontrado!"));

        estoque.setEstoqueAtual(estoque.getEstoqueAtual()+1);

        estoqueRepository.save(estoque);
    }

}
