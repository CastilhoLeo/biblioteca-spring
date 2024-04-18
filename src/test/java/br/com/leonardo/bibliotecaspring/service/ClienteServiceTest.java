package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;

import br.com.leonardo.bibliotecaspring.formatter.Formatter;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteConverter clienteConverter;

    @InjectMocks
    private ClienteService service;

    @Test
    public void localizarPeloId_DeveRetornarUmUsuario(){
        Cliente cliente = ClienteBuilder.umCliente().agora();
        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getSobrenome(),
                cliente.getDataNascimento(),
                cliente.getEndereco(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getGenero());
        Mockito.when(repository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        Mockito.when(clienteConverter.toDto(cliente)).thenReturn(clienteDTO);
        ClienteDTO resultado = service.localizarPeloId(cliente.getId());

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(ClienteDTO.class, resultado.getClass());
    }

}
