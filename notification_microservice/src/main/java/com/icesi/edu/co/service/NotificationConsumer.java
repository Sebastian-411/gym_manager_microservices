package com.icesi.edu.co.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.edu.co.DTO.NotificationDTO;

@Service
public class NotificationConsumer {
    @Autowired
    private NotificationService notificacionService;

    @RabbitListener(queues = "notificacion.queue")
    public void recibirNotification(NotificationDTO notificacion) {
        notificacionService.sendNotification(notificacion);
    }
}