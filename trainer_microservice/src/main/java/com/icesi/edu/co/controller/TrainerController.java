package com.icesi.edu.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.edu.co.entity.Trainer;
import com.icesi.edu.co.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/gym/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @Operation(summary = "Agregar un entrenador", description = "Este endpoint permite agregar un nuevo entrenador al sistema.")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Trainer addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }

    @Operation(summary = "Obtener todos los entrenadores", description = "Este endpoint permite obtener una lista de todos los entrenadores registrados en el sistema.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @Operation(summary = "Obtener un entrenador por su id", description = "Este endpoint permite conocer si existe un entrenador por su id.")
    @GetMapping("/exist/{id}")
    public Boolean existeTrainer(@PathVariable Long id) {
        return trainerService.existeTrainer(id);
    }
}
