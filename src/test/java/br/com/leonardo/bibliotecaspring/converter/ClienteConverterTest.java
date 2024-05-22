package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.ClienteDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteConverterTest {

    @Autowired
    private ClienteConverter clienteConverter;


    @Test
    public void toDto_devePassarUmaEntityParaDto(){
        Cliente cliente = ClienteBuilder.umCliente().agora();

        ClienteDTO clienteDTO = clienteConverter.toDto(cliente);

        Assertions.assertNotNull(clienteDTO);
        Assertions.assertEquals(ClienteDTO.class, clienteDTO.getClass());
    }
    @Test
    public void toEntity_DevePassarUmDtoParaUmEntity(){

        Cliente cliente = ClienteBuilder.umCliente().agora();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();

        Cliente clienteResultado = clienteConverter.toEntity(clienteDTO);

        Assertions.assertNotNull(clienteResultado);
        Assertions.assertEquals(Cliente.class, clienteResultado.getClass());

    }
}
