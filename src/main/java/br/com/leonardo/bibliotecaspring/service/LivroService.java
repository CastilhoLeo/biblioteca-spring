package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.EstoqueConverter;
import br.com.leonardo.bibliotecaspring.converter.LivroConverter;
import br.com.leonardo.bibliotecaspring.dto.EstoqueDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.dto.CadastroLivroRequest;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.exception.LivroNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroConverter livroConverter;

    @Autowired
    private EstoqueService estoqueService;


    @Transactional
    public LivroDTO cadastrarLivro(CadastroLivroRequest livroRequest){

       LivroDTO livroDTO = livroRequest.getLivroDto();
       int estoqueInicial = livroRequest.getEstoqueInicial();

        EstoqueDTO estoqueDto = new EstoqueDTO();
        livroDTO.setEstoqueDto(estoqueDto);

        Livro livro = livroConverter.toEntity(livroDTO);
        livro.getEstoque().setLivro(livro);

        LivroDTO livroDto = livroConverter.toDto(repository.save(livro));
        estoqueService.inserirEstoqueInicial(livro.getId(), estoqueInicial);

        return livroDto;
    }


    public LivroDTO pesquisarPeloId(Long id){
       LivroDTO livro = livroConverter.toDto(repository.findById(id).orElseThrow(()->new LivroNaoEncontradoException()));
       return  livro;
    }

    public LivroDTO editarLivro(Long id, LivroDTO novoLivro){
        Livro livro = repository.findById(id).orElseThrow(()-> new LivroNaoEncontradoException());
        livro.setTitulo(novoLivro.getTitulo());
        livro.setAutor(novoLivro.getAutor());
        livro.setDataPublicacao(novoLivro.getDataPublicacao());
        livro.setEdicao(novoLivro.getEdicao());
        return livroConverter.toDto(repository.save(livro));
    }

    public void deletarLivro(Long id){
        repository.deleteById(id);
    }

    public Page<LivroDTO> pesquisDinamica(String autor, String titulo, Pageable pageable){

        if(autor == "" && titulo == ""){
            Page<LivroDTO> pageLivroDto = repository.findAll(pageable).map((livro)->livroConverter.toDto(livro));
            return pageLivroDto;
        }else {
            Page<LivroDTO> pageLivroDto = repository.pesquisaDinamica(autor, titulo, pageable).map((livro)->livroConverter.toDto(livro));
            return pageLivroDto;
        }
    }


}
