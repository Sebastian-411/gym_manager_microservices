package com.icesi.edu.co.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.icesi.edu.co.DTO.ChangeScheduleDTO;
import com.icesi.edu.co.DTO.InscriptionDTO;
import com.icesi.edu.co.DTO.ReservationDTO;
import com.icesi.edu.co.model.ResumeTraining;

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

    @KafkaListener(topics = "class-reservation", groupId = "class-group")
    public void recibirNotificationReservation(ReservationDTO notificacion) {
        classService.sendNotificationReservation(notificacion);
    }


    @KafkaListener(topics = "resumen-entrenamiento", groupId = "class-group")
    public void recibirResumenEntrenamiento(ResumeTraining resumen) {
        System.out.println("Resumen de entrenamiento recibido: " + resumen.getTotalBooking());
    }

}