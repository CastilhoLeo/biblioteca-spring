package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.converter.LivroConverter;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroConverter livroConverter;

    public Livro cadastrarLivro(LivroDTO livroDTO){
        Livro livro = livroConverter.toEntity(livroDTO);
        return repository.save(livro);
    }

    public LivroDTO pesquisarPeloId(Long id){
       LivroDTO livro = livroConverter.toDto(repository.findById(id).orElseThrow(()->new ValidationException("Livro não encontrado")));
       return  livro;
    }

    public Livro editarLivro(Long id, LivroDTO novoLivro){
        Livro livro = repository.findById(id).orElseThrow(()-> new ValidationException("Livro não encontrado"));
        livro.setTitulo(novoLivro.getTitulo());
        livro.setAutor(novoLivro.getAutor());
        livro.setDataPublicacao(novoLivro.getDataPublicacao());
        livro.setEdicao(novoLivro.getEdicao());
        return repository.save(livro);
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
