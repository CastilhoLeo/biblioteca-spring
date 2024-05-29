package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    public Estoque cadastrarEstoque(Estoque estoque, Livro livro){
        estoque.setLivro(livro);
        return repository.save(estoque);
    }

}
