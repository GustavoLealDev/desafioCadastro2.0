package com.desafiocadastro2.models.enums;

public enum PetSex {

    MACHO("Macho"),
    FEMEA("Fêmea");

    private final String description ;

    PetSex(String description ) {
        this.description  = description ;
    }

    public String getDescription () {
        return description;
    }

}
