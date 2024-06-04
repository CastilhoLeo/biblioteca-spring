package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import org.springframework.stereotype.Service;

@Service
public class LivroConverter {

    public LivroDTO toDto(Livro livro){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());
        livroDTO.setDataPublicacao(livro.getDataPublicacao());
        livroDTO.setEdicao(livro.getEdicao());
        livroDTO.setEstoque(livro.getEstoque());
        return livroDTO;
    }


    public Livro toEntity(LivroDTO livroDTO){
        Livro livro = new Livro();
        livro.setAutor(livroDTO.getAutor());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setDataPublicacao(livroDTO.getDataPublicacao());
        livro.setEdicao(livroDTO.getEdicao());
        livro.setEstoque(livroDTO.getEstoque());
        return livro;
    }
}
