package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.dto.EntradaDTO;
import br.com.leonardo.bibliotecaspring.dto.SalvarEntradaRequest;
import br.com.leonardo.bibliotecaspring.service.EntradaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.ManyToOne;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class EntradaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntradaController entradaController;

    @MockBean
    private EntradaService entradaService;

    @Autowired
    private ObjectMapper mapper;

    public void salvarEntrada_DeveRetornarEntrada(){

        Mockito.when(entradaService.salvarEntrada(any(SalvarEntradaRequest.class))).thenReturn(any(EntradaDTO.class));

       // mockMvc.perform()
    }

}
