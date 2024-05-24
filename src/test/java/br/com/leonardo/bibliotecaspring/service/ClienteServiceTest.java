package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.ClienteDtoBuilder;
import br.com.leonardo.bibliotecaspring.converter.ClienteConverter;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.exception.ValidationException;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;


    @Test
    public void localizarPeloId_DeveRetornarUmUsuario(){
        Cliente cliente = ClienteBuilder.umCliente().agora();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();
        Mockito.when(repository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        ClienteDTO resultado = service.localizarPeloId(cliente.getId());
        System.out.println(resultado);

        assertNotNull(resultado);
        assertEquals(ClienteDTO.class, resultado.getClass());
    }

    @Test
    public void localizarPeloId_DeveRetornarExceptionParaIdNaoEncontrado(){
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        ValidationException ex = assertThrows(ValidationException.class, ()->service.localizarPeloId(2L));

        assertEquals(ex.getMessage(), "Id não localizado!");
    }


    @Test
    public void cadastrarCliente_DeveSalvarUmCLiente(){
        Cliente cliente = ClienteBuilder.umCliente().agora();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();
        Mockito.when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = service.cadastrarCliente(clienteDTO);

        assertNotNull(resultado);
        assertEquals(resultado.getClass(), Cliente.class);
        assertEquals(resultado.getId(), 1L);
    }

    @Test
    public void deletarCliente_deveDeletarClientePeloIdInformado(){
        Cliente cliente = ClienteBuilder.umCliente().agora();
        service.deletarCliente(cliente.getId());

        Mockito.verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void editarCliente_DeveRetornarDadosAlterados(){
        Cliente cliente = ClienteBuilder.umCliente().agora();
        ClienteDTO novoCliente = ClienteDtoBuilder.umCliente().comNome("Joao").agora();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(repository.save(any())).thenReturn(cliente);

        Cliente clienteEditado = service.editarCliente(1L, novoCliente);

        assertEquals(cliente.getNome(), "Joao");
        assertEquals(clienteEditado.getClass(), Cliente.class);
    }

    @Test
    public void editarCliente_DeveRetornarErroPorIdNaoLocalizado(){
        Cliente cliente = ClienteBuilder.umCliente().agora();
        ClienteDTO novoCliente = ClienteDtoBuilder.umCliente().comNome("Joao").agora();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        ValidationException ex = assertThrows(ValidationException.class, ()->service.editarCliente(1L, novoCliente));
        assertEquals(ex.getMessage(), "Cliente nao encontrado");

    }

    @Test
    public void pesquisaDinamica_DeveRetornarTodosOsValores(){

        Pageable pageable = PageRequest.of(0, 10);

        List<Cliente> lista = new ArrayList<>();
        lista.add(ClienteBuilder.umCliente().agora());
        lista.add(ClienteBuilder.umCliente().comId(2L).comNome("Maria").agora());
        lista.add(ClienteBuilder.umCliente().comId(3L).comNome("Joao").agora());

        Page<Cliente> page = new PageImpl<>(lista);
        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn((page));

        Page<ClienteDTO> pageCriado = service.pesquisaDinamica("", "", pageable);

        Mockito.verify(repository, times(1)).findAll(any(Pageable.class));
        Assertions.assertNotNull(pageCriado);
        Assertions.assertEquals(3, pageCriado.getContent().size());

    }

    @Test
    public void pesquisaDinamica_DeveRetornarPeloCPF(){

        Pageable pageable = PageRequest.of(0, 10);

        List<Cliente> lista = new ArrayList<>();
        lista.add(ClienteBuilder.umCliente().agora());

        Page<Cliente> page = new PageImpl<>(lista);
        Mockito.when(repository.pesquisaDinamica(anyString(),anyString(),any(Pageable.class))).thenReturn(page);

        Page<ClienteDTO> pageCriado = service.pesquisaDinamica("", "418", pageable);

        Mockito.verify(repository, times(1)).pesquisaDinamica("","418", pageable);
        Assertions.assertNotNull(pageCriado);
        Assertions.assertEquals(1, pageCriado.getContent().size());
        Assertions.assertEquals(pageCriado.getContent().get(0).getNome(), "Leonardo");

    }

}
