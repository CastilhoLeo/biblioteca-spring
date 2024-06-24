package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.EstoqueDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroConverter {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EstoqueConverter estoqueConverter;

    public LivroDTO toDto(Livro livro){

        LivroDTO livroDto = mapper.map(livro, LivroDTO.class);

        if(livro.getEstoque() != null) {
            EstoqueDTO estoqueDto = estoqueConverter.toDto(livro.getEstoque());
            livroDto.setEstoqueDto(estoqueDto);
        }

        return livroDto;
    }

    public Livro toEntity(LivroDTO livroDto){
        Livro livro = mapper.map(livroDto, Livro.class);

        if(livro.getEstoque() != null) {
            Estoque estoque = estoqueConverter.toEntity(livroDto.getEstoqueDto());
            livro.setEstoque(estoque);
        }

        return livro;
    }

}
