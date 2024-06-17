package br.com.leonardo.bibliotecaspring.exception;

public class PrazoDeLocacaoInvalidoException extends RuntimeException{

    public PrazoDeLocacaoInvalidoException(){
        super("Prazo de locação inválido!");
    }

    public PrazoDeLocacaoInvalidoException(String message){
        super(message);
    }
}
