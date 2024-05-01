package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.ClienteDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClienteConverterTest {

    @Test
    public void toDto_devePassarUmaEntityParaDto(){
        Cliente cliente = ClienteBuilder.umCliente().agora();

        ClienteDTO clienteDTO = new ClienteConverter().toDto(cliente);

        Assertions.assertNotNull(clienteDTO);
        Assertions.assertEquals(ClienteDTO.class, clienteDTO.getClass());
    }
    @Test
    public void toEntity_DevePassarUmDtoParaUmEntity(){

        Cliente cliente = ClienteBuilder.umCliente().agora();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();

        Cliente clienteResultado = new ClienteConverter().toEntity(clienteDTO);

        Assertions.assertNotNull(clienteResultado);
        Assertions.assertEquals(Cliente.class, clienteResultado.getClass());

    }
}
