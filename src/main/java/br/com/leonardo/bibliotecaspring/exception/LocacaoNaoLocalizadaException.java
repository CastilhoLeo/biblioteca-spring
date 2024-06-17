package br.com.leonardo.bibliotecaspring.exception;

public class LocacaoNaoLocalizadaException extends RuntimeException{

    public LocacaoNaoLocalizadaException(){
        super("Locação não localizada!");
    }

    public LocacaoNaoLocalizadaException(String message){
        super(message);
    }
}
