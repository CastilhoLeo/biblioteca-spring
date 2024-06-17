package br.com.leonardo.bibliotecaspring.exception.configException;

import br.com.leonardo.bibliotecaspring.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PrazoDeLocacaoInvalidoException.class)
    private ResponseEntity<RestErrorMessage> prazoDeLocacaoInvalido(PrazoDeLocacaoInvalidoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    private ResponseEntity<RestErrorMessage> livroNaoEncotrado(LivroNaoEncontradoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    private ResponseEntity<RestErrorMessage> restErrorMessage (EstoqueInsuficienteException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    private ResponseEntity<RestErrorMessage> clienteNaoEncotrado(ClienteNaoEncontradoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(EstoqueJaInseridoException.class)
    private ResponseEntity<RestErrorMessage> estoqueJaInserido(EstoqueJaInseridoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(EstoqueNegativoException.class)
    private ResponseEntity<RestErrorMessage> estoqueNegativo(EstoqueNegativoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(LocacaoNaoLocalizadaException.class)
    private ResponseEntity<RestErrorMessage> locacaoNaoLocalizada(LocacaoNaoLocalizadaException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(LivroJaDevolvidoException.class)
    private ResponseEntity<RestErrorMessage> livroJaDevolvido(LivroJaDevolvidoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }
}
