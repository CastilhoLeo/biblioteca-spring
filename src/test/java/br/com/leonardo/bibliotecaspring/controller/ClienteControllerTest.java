package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.builders.ClienteBuilder;
import br.com.leonardo.bibliotecaspring.builders.ClienteDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService service;

    @Autowired
    private ClienteController controller;


    @Test
    public void retornarPeloId_deveRetornarClientePeloId() throws Exception {

        Long clienteId = 1L;
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().comId(1L).agora();
        Mockito.when(service.localizarPeloId(clienteId)).thenReturn(clienteDTO);

        mockMvc.perform(get("/api/clientes/{id}", clienteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", Matchers.is("Leonardo")))
                .andExpect(jsonPath("$.sobrenome", Matchers.is("Castilho")))
                .andExpect(jsonPath("$.dataNascimento", Matchers.is("1992-11-13")))
                .andExpect(jsonPath("$.cpf", Matchers.is("41861297890")))
                .andExpect(jsonPath("$.telefone", Matchers.is("44998240563")))
                .andExpect(jsonPath("$.genero", Matchers.is("MASCULINO")))
                .andExpect(jsonPath("$.endereco", Matchers.is(List.of())));
    }

    @Test
    public void cadastrarCliente_DeveCadastrarUmCliente() throws Exception {

        ClienteDTO cliente = ClienteDtoBuilder.umCliente().agora();
        Mockito.when(service.cadastrarCliente(any(ClienteDTO.class))).thenReturn(new ClienteDTO());

        mockMvc.perform(post("/api/clientes/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated());
    }

    @Test
    public void localizarTodos_deveRetornarUmPageableDeClientes() throws Exception {
        Pageable pageable = PageRequest.of(2,5);
        List<ClienteDTO> listaCliente = new ArrayList<>();
        ClienteDTO clienteDTO = ClienteDtoBuilder.umCliente().agora();
        listaCliente.add(clienteDTO);
        Page<ClienteDTO> pageCliente = new PageImpl<>(listaCliente);
        Mockito.when(service.pesquisaDinamica(anyString(), anyString(), any(Pageable.class))).thenReturn(pageCliente);

        mockMvc.perform(get("/api/clientes")
                        .param("nome","teste")
                        .param("cpf", "123")
                        .param("page","2")
                        .param("size","5"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome", Matchers.is("Leonardo")))
                .andExpect(jsonPath("$.content[0].sobrenome", Matchers.is("Castilho")))
                .andExpect(jsonPath("$.content[0].dataNascimento", Matchers.is("1992-11-13")))
                .andExpect(jsonPath("$.content[0].cpf", Matchers.is("41861297890")))
                .andExpect(jsonPath("$.content[0].telefone", Matchers.is("44998240563")))
                .andExpect(jsonPath("$.content[0].genero", Matchers.is("MASCULINO")))
                .andExpect(jsonPath("$.content[0].endereco", Matchers.is(List.of())));

        Mockito.verify(service,Mockito.times(1)).pesquisaDinamica("teste","123",pageable);
    }

    @Test
    public void deletarCliente_deveDeletarUsuarioERetornarNoContent() throws Exception{
        Long clienteId = 1L;

        Mockito.doNothing().when(service).deletarCliente(clienteId);

        mockMvc.perform(delete("/api/clientes/{id}", clienteId)).andExpect(status().isNoContent());
        Mockito.verify(service, Mockito.times(1)).deletarCliente(1L);
    }

    @Test
    public void editarCliente_DeveRetornarClienteEditado() throws Exception{
        Cliente cliente = ClienteBuilder.cliente().comNome("Roberto").build();
        Long clienteId = 1L;
        ClienteDTO novoCliente = ClienteDtoBuilder.umCliente().comNome("Roberto").agora();
        Mockito.when(service.editarCliente(clienteId,novoCliente)).thenReturn(novoCliente);

            mockMvc.perform(put("/api/clientes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novoCliente)))
                        .andExpect(status().isOk())


                .andExpect(jsonPath("$.nome", Matchers.is("Roberto")))
                .andExpect(jsonPath("$.sobrenome", Matchers.is("Castilho")))
                .andExpect(jsonPath("$.dataNascimento", Matchers.is("1992-11-13")))
                .andExpect(jsonPath("$.cpf", Matchers.is("41861297890")))
                .andExpect(jsonPath("$.telefone", Matchers.is("44998240563")))
                .andExpect(jsonPath("$.genero", Matchers.is("MASCULINO")))
                .andExpect(jsonPath("$.endereco", Matchers.is(List.of())));


        Mockito.verify(service, Mockito.times(1)).editarCliente(1L, novoCliente);

    }
}

