package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.EstoqueDTO;
import br.com.leonardo.bibliotecaspring.entity.Estoque;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueConverter {

    @Autowired
    private ModelMapper mapper;

    public EstoqueDTO toDto(Estoque estoque){
        return mapper.map(estoque, EstoqueDTO.class);
    }
    public Estoque toEntity(EstoqueDTO estoqueDto){
        return mapper.map(estoqueDto, Estoque.class);
    }
}
