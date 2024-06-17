package br.com.leonardo.bibliotecaspring.exception;

public class EstoqueNegativoException extends RuntimeException{

    public EstoqueNegativoException(){
        super("Estoque inicial não pode ser negativo!");
    }

    public EstoqueNegativoException(String message){
        super(message);
    }
}
