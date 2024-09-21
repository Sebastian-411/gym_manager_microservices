package com.icesi.edu.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.icesi.edu.co.entity.Equiment;
import com.icesi.edu.co.service.EquimentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/gym/")
public class EquimentController {
    @Autowired
    private EquimentService equimentService;

    @PostMapping("/equiment")
    @PreAuthorize("hasAnyRole('ROLE_TRAINER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Añadir un nuevo equipo",
            description = "Este endpoint permite añadir un nuevo equipo al gimnasio."
    )
    public Equiment addEquiment(
            @Parameter(description = "El equipo que se va a añadir", required = true)
            @RequestBody Equiment equiment) {   
        return equimentService.addEquiment(equiment);
    }

    @GetMapping("/equiment")
    @PreAuthorize("hasAnyRole('ROLE_TRAINER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Obtener todos los equipos",
            description = "Este endpoint permite obtener una lista de todos los equipos registrados en el sistema."
    )
    public List<Equiment> getAllEquiments() {
        return equimentService.getAllEquiments();
    }
}
