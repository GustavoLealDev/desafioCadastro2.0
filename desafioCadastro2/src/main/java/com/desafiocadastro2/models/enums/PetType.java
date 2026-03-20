package com.desafiocadastro2.models.enums;

public enum PetType {

    CACHORRO("Cachorro"),
    GATO("Gato");

    private final String description ;

    PetType(String description ) {
        this.description  = description ;
    }

    public String getDescription () {
        return description;
    }

}
