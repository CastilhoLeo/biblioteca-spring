package br.com.leonardo.bibliotecaspring.exception;

public class ClienteNaoEncontradoException extends RuntimeException{

    public ClienteNaoEncontradoException(){

        super("Cliente não encontrado!");
    }

    public ClienteNaoEncontradoException(String message){

        super(message);
    }
}
