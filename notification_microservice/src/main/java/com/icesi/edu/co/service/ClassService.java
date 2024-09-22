package com.icesi.edu.co.service;

import org.springframework.stereotype.Service;

import com.icesi.edu.co.DTO.ChangeScheduleDTO;
import com.icesi.edu.co.DTO.InscriptionDTO;
import com.icesi.edu.co.DTO.ReservationDTO;

@Service
public class ClassService {

    public void sendNotificationInscription(InscriptionDTO  notificacion) {
        System.out.println("Notificacion enviada a: " + notificacion.getUserId());
        System.out.println("Mensaje: " + notificacion.getMessage());
    }


    public void sendNotificationChangeSchedule(ChangeScheduleDTO notificacion) {
        System.out.println("Se cambio el horario de la clase con el id: " + notificacion.getClassId());
        System.out.println("Mensaje: " + notificacion.getMessage());
    }

    public void sendNotificationReservation(ReservationDTO notificacion) {
        System.out.println("El usuario con id: " + notificacion.getMemberId());
        System.out.println("Se inscribio a la clase: " + notificacion.getClassId());
        System.out.println("Mensaje: " + notificacion.getMessage());
    }

}
