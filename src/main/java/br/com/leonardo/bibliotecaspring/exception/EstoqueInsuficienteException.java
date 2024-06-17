package br.com.leonardo.bibliotecaspring.exception;

public class EstoqueInsuficienteException extends RuntimeException{

    public EstoqueInsuficienteException(){
        super ("Estoque insuficiente");
    }

    public EstoqueInsuficienteException(String message){
        super(message);
    }
}
