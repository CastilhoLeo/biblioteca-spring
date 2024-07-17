package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.ClienteDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.EnderecoDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import br.com.leonardo.bibliotecaspring.enums.Genero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteConverterTest {

    @Autowired
    private ClienteConverter clienteConverter;


    @Test
    public void toDto_devePassarUmaEntityParaDto(){
        List<Endereco> endereco = new ArrayList<>();
        Endereco endereco2 = new Endereco(1l, "Olimpio Mendes da Rocha","355", "87023015", "ap 8010", "Cidade Nova", new Cliente());
        endereco.add(endereco2);

        Cliente cliente = ClienteBuilder.cliente().comEndereco(endereco).build();

        ClienteDTO clienteDTO = clienteConverter.toDto(cliente);


        Assertions.assertNotNull(clienteDTO);
        Assertions.assertEquals(ClienteDTO.class, clienteDTO.getClass());
        Assertions.assertTrue(clienteDTO.getEndereco().get(0) instanceof EnderecoDTO);

        Assertions.assertEquals(clienteDTO.getId(), 1L);
        Assertions.assertEquals(clienteDTO.getNome(), "Leonardo");
        Assertions.assertEquals(clienteDTO.getSobrenome(), "Castilho");
        Assertions.assertEquals(clienteDTO.getDataNascimento(), LocalDate.of(1992,11,13));
        Assertions.assertEquals(clienteDTO.getEndereco(), List.of(new EnderecoDTO(1L, "Olimpio Mendes da Rocha", "355", "87023015", "ap 8010", "Cidade Nova")));
        Assertions.assertEquals(clienteDTO.getCpf(), "41861297890");
        Assertions.assertEquals(clienteDTO.getTelefone(), "44998240563");
        Assertions.assertEquals(clienteDTO.getGenero(), Genero.MASCULINO);
    }
    @Test
    public void toEntity_DevePassarUmDtoParaUmEntity(){

        List<EnderecoDTO> endereco = new ArrayList<>();
        EnderecoDTO endereco2 = new EnderecoDTO(1l, "Olimpio Mendes da Rocha","355", "87023015", "ap 8010", "Cidade Nova");
        endereco.add(endereco2);
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().comEndereco(endereco).agora();

        Cliente cliente = clienteConverter.toEntity(clienteDTO);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals(Cliente.class, cliente.getClass());
        Assertions.assertTrue(cliente.getEndereco().get(0) instanceof Endereco);

        Assertions.assertEquals(cliente.getId(), 1L);
        Assertions.assertEquals(cliente.getNome(), "Leonardo");
        Assertions.assertEquals(cliente.getSobrenome(), "Castilho");
        Assertions.assertEquals(cliente.getDataNascimento(), LocalDate.of(1992,11,13));
        Assertions.assertEquals(cliente.getEndereco(), List.of(new Endereco(1L, "Olimpio Mendes da Rocha", "355", "87023015", "ap 8010", "teste", cliente)));
        Assertions.assertEquals(cliente.getCpf(), "41861297890");
        Assertions.assertEquals(cliente.getTelefone(), "44998240563");
        Assertions.assertEquals(cliente.getGenero(), Genero.MASCULINO);

    }
}
