package com.desafiocadastro2.services;

import com.desafiocadastro2.exceptions.DomainExceptions;
import com.desafiocadastro2.models.entities.Address;
import com.desafiocadastro2.models.entities.PetEntity;
import com.desafiocadastro2.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private static final String NAO_INFORMADO = "NAO INFORMADO";
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public PetEntity create(PetEntity pet) {
        validateRules(pet);
        validateEmptyFields(pet);
        return petRepository.save(pet);
    }

    public List<PetEntity> findAll() {
        return petRepository.findAll();
    }

    public PetEntity findById(String id) {
        return petRepository.findById(id).orElseThrow(() -> new DomainExceptions("Pet não encontrado"));
    }

    public void delete(String id) {
        PetEntity pet = findById(id);
        petRepository.delete(pet);
    }

    public PetEntity update(String id, PetEntity pet) {
        PetEntity petEntity = findById(id);

        validateRules(pet);
        validateEmptyFields(pet);

        petEntity.setName(pet.getName());
        petEntity.setAge(pet.getAge());
        petEntity.setWeight(pet.getWeight());
        petEntity.setRace(pet.getRace());
        petEntity.setType(pet.getType());
        petEntity.setSex(pet.getSex());
        petEntity.setAddress(pet.getAddress());

        return petRepository.save(petEntity);
    }

    private void validateRules(PetEntity pet) {
        validateWeight(pet.getWeight());
        validateAge(pet.getAge());
    }

    private void validateWeight(Double weight) {
        if (weight != null) {
            if (weight > 60 || weight < 0.5) {
                throw new DomainExceptions("Peso deve estar entre 0.5kg e 60kg");
            }
        }
    }

    private void validateAge(Double age) {
        if (age != null) {
            if (age > 20) {
                throw new DomainExceptions("Idade não pode ser maior que 20 anos");
            }
        }
    }

    private void validateEmptyFields(PetEntity pet) {

        if (isBlank(pet.getRace())) {
            pet.setRace(NAO_INFORMADO);
        }

        if (pet.getAddress() == null) {
            Address address = new Address();
            address.setStreet(NAO_INFORMADO);
            address.setNumber(NAO_INFORMADO);
            address.setNeighborhood(NAO_INFORMADO);
            address.setCity(NAO_INFORMADO);
            pet.setAddress(address);
        } else {
            if (isBlank(pet.getAddress().getStreet())) {
                pet.getAddress().setStreet(NAO_INFORMADO);
            }
            if (isBlank(pet.getAddress().getNumber())) {
                pet.getAddress().setNumber(NAO_INFORMADO);
            }
            if (isBlank(pet.getAddress().getNeighborhood())) {
                pet.getAddress().setNeighborhood(NAO_INFORMADO);
            }
            if (isBlank(pet.getAddress().getCity())) {
                pet.getAddress().setCity(NAO_INFORMADO);
            }
        }
    }
    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}

