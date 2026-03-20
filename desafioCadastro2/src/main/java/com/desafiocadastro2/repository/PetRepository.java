package com.desafiocadastro2.repository;

import com.desafiocadastro2.models.entities.PetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<PetEntity, String> {
}
