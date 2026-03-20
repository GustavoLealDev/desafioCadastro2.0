package com.desafiocadastro2.controller;

import com.desafiocadastro2.dtos.PetRecordDto;
import com.desafiocadastro2.models.entities.PetEntity;
import com.desafiocadastro2.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/pets")
    public ResponseEntity<PetEntity> savePet(@RequestBody @Valid PetRecordDto petRecordDto) {
        PetEntity petEntity = new PetEntity();
        BeanUtils.copyProperties(petRecordDto, petEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.create(petEntity));
    }

    @GetMapping("/pets")
    public ResponseEntity<List<PetEntity>> getAllPets() {
        return ResponseEntity.ok(petService.findAll());
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<PetEntity> getPetById(@PathVariable String id) {
        return ResponseEntity.ok(petService.findById(id));
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<PetEntity> updatePet(
            @PathVariable String id,
            @RequestBody @Valid PetRecordDto petRecordDto) {

        PetEntity petEntity = new PetEntity();
        BeanUtils.copyProperties(petRecordDto, petEntity);

        return ResponseEntity.ok(petService.update(id, petEntity));
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable String id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
