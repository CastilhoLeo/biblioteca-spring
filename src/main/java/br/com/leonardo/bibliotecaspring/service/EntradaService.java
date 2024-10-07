package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.EntradaConverter;
import br.com.leonardo.bibliotecaspring.dto.EntradaDTO;
import br.com.leonardo.bibliotecaspring.dto.SalvarEntradaRequest;
import br.com.leonardo.bibliotecaspring.entity.Entrada;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.exception.LivroNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.repository.EntradaRepository;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EntradaService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private EntradaConverter entradaConverter;

    @Autowired
    private EstoqueService estoqueService;


public EntradaDTO salvarEntrada(SalvarEntradaRequest salvarEntradaRequest){

    Livro livro = livroRepository.findById(salvarEntradaRequest.getIdLivro()).orElseThrow(()->new LivroNaoEncontradoException());

    Entrada entrada = new Entrada();
    entrada.setLivro(livro);
    entrada.setQuantidade(salvarEntradaRequest.getQuantidade());
    entrada.setData(salvarEntradaRequest.getData());

    estoqueService.entradaEstoque(livro.getId(),  salvarEntradaRequest.getQuantidade());
    return entradaConverter.toDto(entradaRepository.save(entrada));

}
}
