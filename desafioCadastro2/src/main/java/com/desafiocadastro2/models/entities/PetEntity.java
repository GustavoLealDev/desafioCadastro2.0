package com.desafiocadastro2.models.entities;


import com.desafiocadastro2.models.enums.PetSex;
import com.desafiocadastro2.models.enums.PetType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "pets")
public class PetEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private Double age;
    private Double weight;
    private String race;
    private PetType type;
    private PetSex sex;
    private Address address;
}
