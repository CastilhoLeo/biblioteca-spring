package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.ClienteDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.EnderecoDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Endereco;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ClienteConverterTest {

    @Autowired
    private ClienteConverter clienteConverter;


    @Test
    public void toDto_devePassarUmaEntityParaDto(){
        List<Endereco> endereco = new ArrayList<>();
        Endereco endereco2 = new Endereco(1l, "Olimpio Mendes da Rocha","355", "87023015", "ap 8010", "teste", new Cliente());
        endereco.add(endereco2);

        Cliente cliente = ClienteBuilder.cliente().comEndereco(endereco).build();

        ClienteDTO clienteDTO = clienteConverter.toDto(cliente);

        System.out.println(clienteDTO);

        Assertions.assertNotNull(clienteDTO);
        Assertions.assertEquals(ClienteDTO.class, clienteDTO.getClass());
        Assertions.assertTrue(clienteDTO.getEndereco().get(0) instanceof EnderecoDTO);
    }
    @Test
    public void toEntity_DevePassarUmDtoParaUmEntity(){

        Cliente cliente = ClienteBuilder.cliente().build();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();

        Cliente clienteResultado = clienteConverter.toEntity(clienteDTO);

        Assertions.assertNotNull(clienteResultado);
        Assertions.assertEquals(Cliente.class, clienteResultado.getClass());

    }
}
