package com.desafiocadastro2.dtos;

import com.desafiocadastro2.models.entities.Address;
import com.desafiocadastro2.models.enums.PetSex;
import com.desafiocadastro2.models.enums.PetType;
import jakarta.validation.constraints.*;

public record PetRecordDto(@NotBlank
                           @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome deve conter apenas letras")
                           String name,

                           @NotNull
                           @DecimalMin(value = "0.0", message = "Idade deve ser positiva")
                           @DecimalMax(value = "20.0", message = "Idade deve ser menor ou igual a 20")
                           Double age,

                           @DecimalMin(value = "0.5", message = "Peso deve ser no mínimo 0.5kg")
                           @DecimalMax(value = "60.0", message = "Peso deve ser no máximo 60kg")
                           Double weight,

                           @Pattern(regexp = "^[A-Za-zÀ-ÿ ]*$", message = "Raça deve conter apenas letras")
                           String race,

                           @NotNull
                           PetType type,

                           @NotNull
                           PetSex sex,

                           Address address) {
}
