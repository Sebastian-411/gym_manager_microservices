package com.icesi.edu.co.service;

import org.springframework.stereotype.Service;

import com.icesi.edu.co.DTO.ChangeScheduleDTO;
import com.icesi.edu.co.DTO.InscriptionDTO;

@Service
public class ClassService {

    public void sendNotificationInscription(InscriptionDTO  notificacion) {
        System.out.println("Notificacion enviada a: " + notificacion.getUserId());
        System.out.println("Mensaje: " + notificacion.getMessage());
    }


    public void sendNotificationChangeSchedule(ChangeScheduleDTO notificacion) {
        System.out.println("Notificacion enviada a: " + notificacion.getClassId());
        System.out.println("Mensaje: " + notificacion.getMessage());
    }

}
