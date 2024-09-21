package com.icesi.edu.co.service;

import org.springframework.stereotype.Service;

import com.icesi.edu.co.DTO.NotificationDTO;

@Service
public class NotificationService {

    public void sendNotification(NotificationDTO notificacion) {
        System.out.println("Notificacion enviada a: " + notificacion.getUserId());
        System.out.println("Mensaje: " + notificacion.getMessage());
    }
}
