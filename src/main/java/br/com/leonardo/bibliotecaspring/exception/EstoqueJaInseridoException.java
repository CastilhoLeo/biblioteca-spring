package br.com.leonardo.bibliotecaspring.exception;

public class EstoqueJaInseridoException extends RuntimeException{

    public EstoqueJaInseridoException(){
        super("Estoque inicial já cadastrado!");
    }

    public EstoqueJaInseridoException(String message){
        super(message);
    }
}
