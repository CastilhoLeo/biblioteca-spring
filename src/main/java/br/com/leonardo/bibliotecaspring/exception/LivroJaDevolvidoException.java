package br.com.leonardo.bibliotecaspring.exception;

public class LivroJaDevolvidoException extends RuntimeException{

    public LivroJaDevolvidoException(){
        super("Este livro já foi devolvido!");
    }

    public LivroJaDevolvidoException(String message){
        super(message);
    }
}
