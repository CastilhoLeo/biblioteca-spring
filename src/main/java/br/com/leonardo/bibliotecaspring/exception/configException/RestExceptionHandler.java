package br.com.leonardo.bibliotecaspring.exception.configException;

import br.com.leonardo.bibliotecaspring.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PrazoDeLocacaoInvalidoException.class)
    public ResponseEntity<RestErrorMessage> prazoDeLocacaoInvalido(PrazoDeLocacaoInvalidoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<RestErrorMessage> livroNaoEncotrado(LivroNaoEncontradoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<RestErrorMessage> estoqueInsuficiente (EstoqueInsuficienteException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<RestErrorMessage> clienteNaoEncotrado(ClienteNaoEncontradoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(EstoqueJaInseridoException.class)
    public ResponseEntity<RestErrorMessage> estoqueJaInserido(EstoqueJaInseridoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(EstoqueNegativoException.class)
    public ResponseEntity<RestErrorMessage> estoqueNegativo(EstoqueNegativoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(LocacaoNaoLocalizadaException.class)
    public ResponseEntity<RestErrorMessage> locacaoNaoLocalizada(LocacaoNaoLocalizadaException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(LivroJaDevolvidoException.class)
    public ResponseEntity<RestErrorMessage> livroJaDevolvido(LivroJaDevolvidoException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestErrorMessage> erroConstraint(ConstraintViolationException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }
}
