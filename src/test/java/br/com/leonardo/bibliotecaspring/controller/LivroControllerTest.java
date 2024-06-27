package br.com.leonardo.bibliotecaspring.controller;

import br.com.leonardo.bibliotecaspring.builders.LivroDtoBuilder;
import br.com.leonardo.bibliotecaspring.dto.CadastroLivroRequest;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.service.LivroService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LivroController livroController;

    @Test
    public void localizarPeloId() throws Exception{

        LivroDTO livroDTO = LivroDtoBuilder.umLivro().agora();
        Mockito.when(livroService.pesquisarPeloId(anyLong())).thenReturn(livroDTO);

        mockMvc.perform(get("/api/livros/{id}", livroDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.titulo", Matchers.is("Harry Potter")))
                .andExpect(jsonPath("$.autor", Matchers.is("J.K. Rowling")))
                .andExpect(jsonPath("$.edicao", Matchers.is(1)))
                .andExpect(jsonPath("$.dataPublicacao", Matchers.is("1997-06-26")))
                .andExpect(jsonPath("$.estoqueDto.estoqueAtual", Matchers.is(0)))
                .andExpect(jsonPath("$.estoqueDto.estoqueInicial", Matchers.is(0)))
                .andExpect(jsonPath("$.estoqueDto.situacaoLivro", Matchers.nullValue()));
    }

    @Test
    public void cadastrarLivro() throws Exception{

        LivroDTO livroDto = LivroDtoBuilder.umLivro().agora();
        CadastroLivroRequest request = new CadastroLivroRequest(livroDto, 1);
        Mockito.when(livroService.cadastrarLivro(any(CadastroLivroRequest.class))).thenReturn(livroDto);


        mockMvc.perform(post("/api/livros/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.titulo", Matchers.is("Harry Potter")))
                .andExpect(jsonPath("$.autor", Matchers.is("J.K. Rowling")))
                .andExpect(jsonPath("$.edicao", Matchers.is(1)))
                .andExpect(jsonPath("$.dataPublicacao", Matchers.is("1997-06-26")))
                .andExpect(jsonPath("$.estoqueDto.estoqueAtual", Matchers.is(0)))
                .andExpect(jsonPath("$.estoqueDto.estoqueInicial", Matchers.is(0)))
                .andExpect(jsonPath("$.estoqueDto.situacaoLivro", Matchers.nullValue()));
    }

    @Test
    public void deletarLivro() throws Exception{

        Mockito.doNothing().when(livroService).deletarLivro(anyLong());
        mockMvc.perform(delete("/api/livros/{id}", 1))
                .andExpect(status().isNoContent());

        Mockito.verify(livroService, Mockito.times(1)).deletarLivro(1L);
    }

    @Test
    public void editarLiro() throws Exception{

        LivroDTO livro = LivroDtoBuilder.umLivro().agora();
        Mockito.when(livroService.editarLivro(anyLong(), any(LivroDTO.class))).thenReturn(livro);

        mockMvc.perform(put("/api/livros/{id}", livro.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livro)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.titulo", Matchers.is("Harry Potter")))
                .andExpect(jsonPath("$.autor", Matchers.is("J.K. Rowling")))
                .andExpect(jsonPath("$.edicao", Matchers.is(1)))
                .andExpect(jsonPath("$.dataPublicacao", Matchers.is("1997-06-26")))
                .andExpect(jsonPath("$.estoqueDto.estoqueAtual", Matchers.is(0)))
                .andExpect(jsonPath("$.estoqueDto.estoqueInicial", Matchers.is(0)))
                .andExpect(jsonPath("$.estoqueDto.situacaoLivro", Matchers.nullValue()));
    }

    @Test
    public void pesquisaDinamica() throws Exception{

        LivroDTO livro = LivroDtoBuilder.umLivro().agora();
        List<LivroDTO> list = new ArrayList<>();
        list.add(livro);
        Page<LivroDTO> page = new PageImpl<>(list);

        Mockito.when(livroService.pesquisDinamica(anyString(),anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/livros")
                        .param("titulo", "Harry")
                        .param("autor", "J.K.")
                        .param("page", "2")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.content[0].autor", Matchers.is("J.K. Rowling")))
                .andExpect(jsonPath("$.content[0].titulo", Matchers.is("Harry Potter")))
                .andExpect(jsonPath("$.content[0].edicao", Matchers.is(1)))
                .andExpect(jsonPath("$.content[0].dataPublicacao", Matchers.is("1997-06-26")))
                .andExpect(jsonPath("$.content[0].estoqueDto.estoqueAtual", Matchers.is(0)))
                .andExpect(jsonPath("$.content[0].estoqueDto.estoqueInicial", Matchers.is(0)))
                .andExpect(jsonPath("$.content[0].estoqueDto.situacaoLivro", Matchers.nullValue()));
    }
}
