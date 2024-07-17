package br.com.leonardo.bibliotecaspring.exception;

import br.com.leonardo.bibliotecaspring.exception.configException.RestErrorMessage;
import br.com.leonardo.bibliotecaspring.exception.configException.RestExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class RestExceptionHandlerTest {

   @Autowired
    private MockMvc mockMvc;

   @Autowired
   private RestExceptionHandler restExceptionHandler;

    @Test
    public void prazoDeLocacaoInvalido(){
        ResponseEntity<RestErrorMessage> response = restExceptionHandler.prazoDeLocacaoInvalido(new PrazoDeLocacaoInvalidoException());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getStatus());
        Assertions.assertEquals("Prazo de locação inválido!", response.getBody().getMessage());
    }

    @Test
    public void livroNaoEncontradoException(){

        ResponseEntity<RestErrorMessage> response = restExceptionHandler.livroNaoEncotrado(new LivroNaoEncontradoException());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getBody().getStatus());
        Assertions.assertEquals("Livro não encontrado!", response.getBody().getMessage());
    }

    @Test
    public void estoqueInsuficiente(){

        ResponseEntity<RestErrorMessage> response = restExceptionHandler.estoqueInsuficiente(new EstoqueInsuficienteException());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getStatus());
        Assertions.assertEquals("Estoque insuficiente", response.getBody().getMessage());
    }

    @Test
    public void clienteNaoEncontradoException(){

    ResponseEntity<RestErrorMessage> response = restExceptionHandler.clienteNaoEncotrado(new ClienteNaoEncontradoException());
       Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getBody().getStatus());
       Assertions.assertEquals("Cliente não encontrado!", response.getBody().getMessage());
   }

    @Test
    public void estoqueJaInserido(){

        ResponseEntity<RestErrorMessage> response = restExceptionHandler.estoqueJaInserido(new EstoqueJaInseridoException());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getStatus());
        Assertions.assertEquals("Estoque inicial já cadastrado!", response.getBody().getMessage());
    }

    @Test
    public void estoqueNegativo(){

        ResponseEntity<RestErrorMessage> response = restExceptionHandler.estoqueNegativo(new EstoqueNegativoException());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getStatus());
        Assertions.assertEquals("Estoque inicial não pode ser negativo!", response.getBody().getMessage());
    }

    @Test
    public void locacaoNaoLocalizada(){

        ResponseEntity<RestErrorMessage> response = restExceptionHandler.locacaoNaoLocalizada(new LocacaoNaoLocalizadaException());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getBody().getStatus());
        Assertions.assertEquals("Locação não localizada!", response.getBody().getMessage());
    }

    @Test
    public void livroJaDevolvido(){

        ResponseEntity<RestErrorMessage> response = restExceptionHandler.livroJaDevolvido(new LivroJaDevolvidoException());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getStatus());
        Assertions.assertEquals("Este livro já foi devolvido!", response.getBody().getMessage());
    }

}
