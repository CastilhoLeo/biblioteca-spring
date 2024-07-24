package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.EnderecoDTO;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoConverter {

    @Autowired
    private ModelMapper mapper;

    public EnderecoDTO toDto (Endereco endereco){

        return mapper.map(endereco, EnderecoDTO.class);
    }

    public Endereco toEntity (EnderecoDTO enderecoDto){

        return mapper.map(enderecoDto, Endereco.class);
    }
}
