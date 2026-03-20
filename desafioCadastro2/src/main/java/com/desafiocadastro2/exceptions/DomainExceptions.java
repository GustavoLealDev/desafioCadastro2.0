package com.desafiocadastro2.exceptions;

public class DomainExceptions extends RuntimeException{

    public DomainExceptions(String message){
        super(message);
    }
    public DomainExceptions(Throwable cause) {
        super(cause);
    }
    public DomainExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
