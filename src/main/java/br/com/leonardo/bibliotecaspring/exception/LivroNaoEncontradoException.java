package br.com.leonardo.bibliotecaspring.exception;

public class LivroNaoEncontradoException extends RuntimeException{

    public LivroNaoEncontradoException(){
        super("Livro não encontrado!");
    }

    public LivroNaoEncontradoException(String message){
        super(message);
    }
}
