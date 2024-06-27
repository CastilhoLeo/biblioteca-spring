package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.dto.LocacaoDTO;
import br.com.leonardo.bibliotecaspring.dto.SalvarLocacaoRequest;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import br.com.leonardo.bibliotecaspring.enums.PrazoLocacao;
import br.com.leonardo.bibliotecaspring.service.LocacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LocacaoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocacaoService locacaoService;

    @InjectMocks
    private LocacaoController locacaoController;

    @Test
    public void cadastrarLocacao () throws Exception{

        SalvarLocacaoRequest request = new SalvarLocacaoRequest(1L, 1L, PrazoLocacao.DIAS_15);
        LocacaoDTO locacao = new LocacaoDTO();
        Mockito.when(locacaoService.salvarLocacao(any(SalvarLocacaoRequest.class))).thenReturn(locacao);

        mockMvc.perform(post("/locacoes/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clienteDto", Matchers.nullValue()))
                .andExpect(jsonPath("$.livroDto", Matchers.nullValue()))
                .andExpect(jsonPath("$.prazoLocacao", Matchers.nullValue()))
                .andExpect(jsonPath("$.dataSaida", Matchers.nullValue()))
                .andExpect(jsonPath("$.dataPrevistaDevolucao", Matchers.nullValue()))
                .andExpect(jsonPath("$.dataEfetivaDevolucao", Matchers.nullValue()));
    }

    @Test
    public void devolverLocacao() throws Exception{

        LocacaoDTO locacaoDto = new LocacaoDTO();
        Mockito.when(locacaoService.devolverLocacao(anyLong())).thenReturn(locacaoDto);

        mockMvc.perform(put("/locacoes/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteDto", Matchers.nullValue()))
                .andExpect(jsonPath("$.livroDto", Matchers.nullValue()))
                .andExpect(jsonPath("$.prazoLocacao", Matchers.nullValue()))
                .andExpect(jsonPath("$.dataSaida", Matchers.nullValue()))
                .andExpect(jsonPath("$.dataPrevistaDevolucao", Matchers.nullValue()))
                .andExpect(jsonPath("$.dataEfetivaDevolucao", Matchers.nullValue()));
    }

}
