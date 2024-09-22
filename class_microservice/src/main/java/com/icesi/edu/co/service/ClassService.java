package com.icesi.edu.co.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icesi.edu.co.DTO.ChangeScheduleDTO;
import com.icesi.edu.co.DTO.ChangeScheduleRequest;
import com.icesi.edu.co.DTO.ReservationDTO;
import com.icesi.edu.co.DTO.ReservationRequest;
import com.icesi.edu.co.model.Class;
import com.icesi.edu.co.repository.ClassRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private KafkaTemplate<String, ReservationDTO> kafkaTemplate;

    public List<Class> getAllClass() {
        return classRepository.findAll();
    }

    public Class updateClass(Class updatedClass) {
        Class savedClass = classRepository.save(updatedClass);
        return savedClass;
    }

    public void notifyScheduleChange(String classDetails) {
        rabbitTemplate.convertAndSend("schedule-change-exchange", "", classDetails);
    }

    public Class programClass(Class cl) {
        Boolean trainerAvalaible = restTemplate.getForObject(
                "http://localhost:8081/api/gym/trainer/exist/" + cl.getIdTrainer().getIdTrainer(), Boolean.class);

        if (!trainerAvalaible) {
            throw new RuntimeException("El entrenador no existe o no está disponible");
        }

        Class savedClass = classRepository.save(cl);
        notifyScheduleChange("Nueva clase programada: " + savedClass.getName());

        return savedClass;
    }

    public Class changeSchedule(ChangeScheduleRequest newSchedule) {
        Optional<Class> classOptional = classRepository.findById(Long.valueOf(newSchedule.getClassId()));

        if (!classOptional.isPresent()) {
            throw new RuntimeException("La clase no existe");
        }

        Class classToUpdate = classOptional.get();
        classToUpdate.setSchedule(newSchedule.getNewSchedule());

        Class updatedClass = classRepository.save(classToUpdate);

        ChangeScheduleDTO changeScheduleDTO = new ChangeScheduleDTO(updatedClass.getId(),
                "Horario de clase actualizado");
        rabbitTemplate.convertAndSend("classes.exchange", "classes.routingkey", changeScheduleDTO);

        return updatedClass;
    }

    public Class reserveClass(ReservationRequest reservationRequest) {
        Optional<Class> classOptional = classRepository.findById(reservationRequest.getClassId());

        if (!classOptional.isPresent()) {
            throw new RuntimeException("La clase no existe");
        }

        Class classToReserve = classOptional.get();

        if (classToReserve.getCurrentCapacity() >= classToReserve.getMaximumCapacity()) {
            throw new RuntimeException("La clase está llena");
        }

        Boolean memberAvalaible = restTemplate.getForObject(
                "http://localhost:8082/api/gym/member/exist/" + reservationRequest.getMemberId(), Boolean.class);

        if (!memberAvalaible) {
            throw new RuntimeException("El miembro no existe o no está disponible");
        }

        classToReserve.setCurrentCapacity(classToReserve.getCurrentCapacity() + 1);
        Class updatedClass = classRepository.save(classToReserve);

        ReservationDTO reservationDTO = new ReservationDTO(reservationRequest.getClassId(),
                reservationRequest.getMemberId(), "Clase reservada");

        kafkaTemplate.send("class-reservation", reservationDTO);
        return updatedClass;
    }
}
