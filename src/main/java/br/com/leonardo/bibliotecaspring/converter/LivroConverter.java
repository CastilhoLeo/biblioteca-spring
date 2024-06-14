package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroConverter {

    @Autowired
    private ModelMapper mapper;

    public LivroDTO toDto(Livro livro){
        return mapper.map(livro, LivroDTO.class);
    }

    public Livro toEntity(LivroDTO livroDto){
        return mapper.map(livroDto, Livro.class);
    }

}
