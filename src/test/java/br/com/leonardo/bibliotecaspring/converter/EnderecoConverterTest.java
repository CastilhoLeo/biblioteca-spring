package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.dto.EnderecoDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnderecoConverterTest {

    @Autowired
    private EnderecoConverter converter;

    @Test
    public void toDto_DevePassarUmEnderecoParaDto(){
        Endereco endereco = new Endereco(
                1L, "Olimpio Mendes da Rocha", "355", "87023015", "ap 801", "Cidade Nova", new Cliente());
        EnderecoDTO enderecoDto = converter.toDto(endereco);

        Assertions.assertNotNull(enderecoDto);
        Assertions.assertEquals(enderecoDto.getClass(), EnderecoDTO.class);
        Assertions.assertEquals(enderecoDto.getId(), 1L);
        Assertions.assertEquals(enderecoDto.getCep(), "87023015");
        Assertions.assertEquals(enderecoDto.getComplemento(), "ap 801");
        Assertions.assertEquals(enderecoDto.getNumero(), "355");
        Assertions.assertEquals(enderecoDto.getBairro(), "Cidade Nova");

    }

    @Test
    public void toEntity_DevePassarUmEnderecoDtoParaEntity(){
        EnderecoDTO enderecoDto = new EnderecoDTO(
                1L, "Olimpio Mendes da Rocha", "355", "87023015", "ap 801", "Cidade Nova");
        Endereco endereco = converter.toEntity(enderecoDto);

        Assertions.assertNotNull(endereco);
        Assertions.assertEquals(endereco.getClass(), Endereco.class);
        Assertions.assertEquals(endereco.getId(), 1L);
        Assertions.assertEquals(endereco.getCep(), "87023015");
        Assertions.assertEquals(endereco.getComplemento(), "ap 801");
        Assertions.assertEquals(endereco.getNumero(), "355");
        Assertions.assertEquals(endereco.getBairro(), "Cidade Nova");
    }

}
