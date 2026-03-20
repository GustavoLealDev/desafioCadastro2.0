package com.desafiocadastro2.services;

import com.desafiocadastro2.exceptions.DomainExceptions;
import com.desafiocadastro2.models.entities.PetEntity;
import com.desafiocadastro2.models.enums.PetSex;
import com.desafiocadastro2.models.enums.PetType;
import com.desafiocadastro2.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    private PetEntity pet;

    @BeforeEach
    void setUp() {
        pet = new PetEntity();
        pet.setId("1");
        pet.setName("Thor");
        pet.setAge(3.0);
        pet.setWeight(10.0);
        pet.setType(PetType.CACHORRO);
        pet.setSex(PetSex.MACHO);
    }

    @Test
    @DisplayName("Salva o pet com sucesso")
    void shouldSavePetSuccessfully() {
        when(petRepository.save(any())).thenReturn(pet);

        PetEntity result = petService.create(pet);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Thor");
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    @DisplayName("Atualiza o pet com sucesso")
    void shouldUpdatePetSuccessfully() {
        PetEntity newPet = new PetEntity();
        newPet.setName("Thorino");
        newPet.setAge(4.0);
        newPet.setWeight(10.0);
        newPet.setType(PetType.CACHORRO);
        newPet.setSex(PetSex.MACHO);

        when(petRepository.findById(any())).thenReturn(Optional.of(newPet));
        when(petRepository.save(any())).thenReturn(newPet);

        PetEntity result = petService.update("1", newPet);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Thorino");
        assertThat(result.getAge()).isEqualTo(4.0);
    }

    @Test
    @DisplayName("Deleta o pet com sucesso")
    void shouldDeletePetSuccessfully() {
        when(petRepository.findById("1")).thenReturn(Optional.of(pet));
        doNothing().when(petRepository).delete(pet);

        petService.delete("1");

        verify(petRepository, times(1)).delete(pet);
    }

    @Test
    @DisplayName("Busca o pet por ID")
    void shouldFindById() {
        when(petRepository.findById("1")).thenReturn(Optional.of(pet));

        PetEntity result = petService.findById("1");

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
    }

    @Test
    @DisplayName("Retorna todos os pets")
    void shouldFindAllPets() {
        when(petRepository.findAll()).thenReturn(Arrays.asList(pet));

        List<PetEntity> result = petService.findAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("Lança exceção se o pet não for encontrado")
    void shouldExceptionWhenPetNotFound() {
        when(petRepository.findById("1")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> petService.findById("1")).isInstanceOf(DomainExceptions.class).hasMessage("Pet não encontrado");
    }

    @Test
    @DisplayName("Lança exceção se a idade for maior que 20 anos")
    void shouldThrowWhenAgeIsGreaterThan20() {
        pet.setAge(21.0);

        assertThatThrownBy(() -> petService.create(pet)).isInstanceOf(DomainExceptions.class).hasMessage("Idade não pode ser maior que 20 anos");
    }


    @Test
    @DisplayName("Lança exceção se o peso for maior que 60 Kg")
    void shouldThrowWhenWeightIsGreaterThan60() {
        pet.setWeight(61.0);

        assertThatThrownBy(() -> petService.create(pet)).isInstanceOf(DomainExceptions.class).hasMessage("Peso deve estar entre 0.5kg e 60kg");
    }

    @Test
    @DisplayName("Lança exceção se o peso for menor que 0.5 Kg")
    void shouldThrowWhenWeightIsLessThan05() {
        pet.setWeight(0.1);

        assertThatThrownBy(() -> petService.create(pet)).isInstanceOf(DomainExceptions.class).hasMessage("Peso deve estar entre 0.5kg e 60kg");
    }
}