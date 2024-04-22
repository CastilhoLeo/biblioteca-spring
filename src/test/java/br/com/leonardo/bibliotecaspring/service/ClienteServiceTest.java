package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

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

    @Test
    public void localizarPeloId_DeveRetornarExceptionParaIdNaoEncontrado(){
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());

        ValidationException ex = Assertions.assertThrows(ValidationException.class, ()->service.localizarPeloId(2L));

        Assertions.assertEquals(ex.getMessage(), "Id não localizado!");
    }

    @Test
    public void localizarTodos_DeveRetornarUmaListaDeClientes(){
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

        ArrayList<Cliente> listaClientes= new ArrayList<>();
        listaClientes.add(cliente);

        Mockito.when(repository.findAll()).thenReturn(listaClientes);
        Mockito.when(clienteConverter.toDto(cliente)).thenReturn(clienteDTO);
        List<ClienteDTO> resultado = service.localizarTodos();


        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado instanceof List<ClienteDTO>);
    }

    @Test
    public void cadastrarCliente_DeveSalvarUmCLiente(){
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
        Mockito.when(repository.save(any())).thenReturn(cliente);

        Cliente resultado = service.cadastrarCliente(clienteDTO);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(resultado.getClass(), Cliente.class);
        Assertions.assertEquals(resultado.getId(), 1L);
    }

}
