package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.ClienteDtoBuilder;
import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.exception.ClienteNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.exception.LivroNaoEncontradoException;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteConverter clienteConverter;

    @InjectMocks
    private ClienteService service;


    @Test
    public void localizarPeloId_DeveRetornarUmUsuario(){
        Cliente cliente = ClienteBuilder.cliente().build();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();
        Mockito.when(repository.findById(cliente.getId())).thenReturn(Optional.of(cliente));

        Mockito.when(clienteConverter.toDto(any(Cliente.class))).thenReturn(clienteDTO);
        ClienteDTO resultado = service.localizarPeloId(cliente.getId());

        assertNotNull(resultado);
        assertEquals(ClienteDTO.class, resultado.getClass());
        Mockito.verify(repository, times(1)).findById(1L);
        Mockito.verify(clienteConverter, times(1)).toDto(cliente);

    }

    @Test
    public void localizarPeloId_DeveRetornarExceptionParaIdNaoEncontrado(){
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        ClienteNaoEncontradoException ex = assertThrows(ClienteNaoEncontradoException.class, ()->service.localizarPeloId(2L));

        assertEquals(ex.getMessage(), "Cliente não encontrado!");
    }

    @Test
    public void cadastrarCliente_DeveSalvarUmCLiente(){
        Cliente cliente = ClienteBuilder.cliente().build();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();
        Mockito.when(clienteConverter.toEntity(any(ClienteDTO.class))).thenReturn(cliente);
        Mockito.when(clienteConverter.toDto(any(Cliente.class))).thenReturn(clienteDTO);
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO resultado = service.cadastrarCliente(clienteDTO);

        System.out.println(resultado);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(resultado.getClass(), ClienteDTO.class);
        Assertions.assertEquals(resultado.getId(), 1L);
        Mockito.verify(clienteConverter, times(1)).toDto(cliente);
        Mockito.verify(clienteConverter, times(1)).toEntity(clienteDTO);
        Mockito.verify(repository, times(1)).save(cliente);

    }

    @Test
    public void deletarCliente_deveDeletarClientePeloIdInformado(){
        Cliente cliente = ClienteBuilder.cliente().build();
        service.deletarCliente(cliente.getId());

        Mockito.verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void editarCliente_DeveRetornarDadosAlterados(){
        Cliente cliente = ClienteBuilder.cliente().build();
        ClienteDTO novoCliente = ClienteDtoBuilder.umCliente().comNome("Joao").agora();
        Cliente cliente2 = ClienteBuilder.cliente().comNome("Joao").build();

        Mockito.when(clienteConverter.toEntity(any(ClienteDTO.class))).thenReturn(cliente2);
        Mockito.when(clienteConverter.toDto(any(Cliente.class))).thenReturn(novoCliente);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO clienteEditado = service.editarCliente(1L, novoCliente);

        assertEquals(cliente.getNome(), "Joao");
        assertEquals(clienteEditado.getClass(), ClienteDTO.class);
    }

    @Test
    public void editarCliente_DeveRetornarErroPorIdNaoLocalizado(){
        Cliente cliente = ClienteBuilder.cliente().build();
        ClienteDTO novoCliente = ClienteDtoBuilder.umCliente().comNome("Joao").agora();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException ex = assertThrows(ClienteNaoEncontradoException.class, ()->service.editarCliente(1L, novoCliente));
        assertEquals(ex.getMessage(), "Cliente não encontrado!");

    }

    @Test
    public void pesquisaDinamica_DeveRetornarTodosOsValores(){

        Pageable pageable = PageRequest.of(0, 10);

        List<Cliente> lista = new ArrayList<>();
        Cliente cliente1 = ClienteBuilder.cliente().build();
        Cliente cliente2 = ClienteBuilder.cliente().comId(2L).comNome("Maria").build();
        Cliente cliente3 = ClienteBuilder.cliente().comId(3L).comNome("Joao").build();

        lista.add(cliente1);
        lista.add(cliente2);
        lista.add(cliente3);

        ClienteDTO clienteDto1 = ClienteDtoBuilder.umCliente().agora();
        ClienteDTO clienteDto2 = ClienteDtoBuilder.umCliente().comId(2L).comNome("Maria").agora();
        ClienteDTO clienteDto3 = ClienteDtoBuilder.umCliente().comId(3L).comNome("Joao").agora();

        Page<Cliente> page = new PageImpl<>(lista);
        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn((page));
        Mockito.when(clienteConverter.toDto(cliente1)).thenReturn(clienteDto1);
        Mockito.when(clienteConverter.toDto(cliente2)).thenReturn(clienteDto2);
        Mockito.when(clienteConverter.toDto(cliente3)).thenReturn(clienteDto3);

        Page<ClienteDTO> pageCriado = service.pesquisaDinamica("", "", pageable);

        Mockito.verify(repository, times(1)).findAll(any(Pageable.class));
        Assertions.assertNotNull(pageCriado);
        Assertions.assertEquals(3, pageCriado.getContent().size());
        Assertions.assertEquals(pageCriado.getContent().get(0).getNome(), "Leonardo");
        Assertions.assertEquals(pageCriado.getContent().get(1).getNome(), "Maria");
        Assertions.assertEquals(pageCriado.getContent().get(2).getNome(), "Joao");

    }

    @Test
    public void pesquisaDinamica_DeveRetornarPeloCPF(){

        Pageable pageable = PageRequest.of(0, 10);

        List<Cliente> lista = new ArrayList<>();
        lista.add(ClienteBuilder.cliente().build());

        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();

        Page<Cliente> page = new PageImpl<>(lista);
        Mockito.when(repository.pesquisaDinamica(anyString(),anyString(),any(Pageable.class))).thenReturn(page);
        Mockito.when(clienteConverter.toDto(any(Cliente.class))).thenReturn(clienteDTO);

        Page<ClienteDTO> pageCriado = service.pesquisaDinamica("", "418", pageable);

        Mockito.verify(repository, times(1)).pesquisaDinamica("","418", pageable);
        Assertions.assertNotNull(pageCriado);
        Assertions.assertEquals(1, pageCriado.getContent().size());
        Assertions.assertEquals(pageCriado.getContent().get(0).getNome(), "Leonardo");

    }

}
