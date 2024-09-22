
package com.icesi.edu.co.controller;

import com.icesi.edu.co.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.icesi.edu.co.DTO.ChangeScheduleDTO;
import com.icesi.edu.co.DTO.ChangeScheduleRequest;
import com.icesi.edu.co.DTO.ReservationRequest;
import com.icesi.edu.co.model.Class;
import io.swagger.v3.oas.annotations.Operation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/gym/class")
public class ClassController {

    @Autowired
    private ClassService gymService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    @Operation(summary = "Programar una nueva clase", description = "Permite a un entrenador programar una nueva clase en el sistema.")
    public Class programClass(@RequestBody Class cl) {
        return gymService.programClass(cl);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    @Operation(summary = "Obtener todas las clases", description = "Recupera una lista de todas las clases programadas en el gimnasio.")
    public List<Class> getAllClass() {
        return gymService.getAllClass();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    @Operation(summary = "Actualizar una clase", description = "Permite a un entrenador actualizar el horario de una clase programada.")
    public Class changeSchedule(@RequestBody ChangeScheduleRequest newChangeSchedule) {
        return gymService.changeSchedule(newChangeSchedule);
    }

    @PostMapping("/reservation")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    @Operation(summary = "Reservar una clase", description = "Permite a un miembro reservar una clase programada.")
    public Class reserveClass(@RequestBody ReservationRequest reservationRequest) {
        return gymService.reserveClass(reservationRequest);
    }
}
