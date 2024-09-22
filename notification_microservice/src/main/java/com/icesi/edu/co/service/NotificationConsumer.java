package com.icesi.edu.co.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.edu.co.DTO.ChangeScheduleDTO;
import com.icesi.edu.co.DTO.InscriptionDTO;

@Service
public class NotificationConsumer {
    @Autowired
    private ClassService classService;

    @RabbitListener(queues = "inscription.queue")
    public void recibirNotificationInscription(InscriptionDTO notificacion) {
        classService.sendNotificationInscription(notificacion);
    }

    @RabbitListener(queues = "change_schedule.queue")
    public void recibirNotificationSchedule(ChangeScheduleDTO notificacion) {
        classService.sendNotificationChangeSchedule(notificacion);
    }

}