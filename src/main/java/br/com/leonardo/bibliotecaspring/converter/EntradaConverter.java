package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.EntradaDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.entity.Entrada;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntradaConverter {

    @Autowired
    private ModelMapper modelMapper;


    public EntradaDTO toDto(Entrada entrada){

        return modelMapper.map(entrada, EntradaDTO.class);

    }

    public Entrada toEntity(EntradaDTO entradaDTO){

        return modelMapper.map(entradaDTO, Entrada.class);
    }


}


