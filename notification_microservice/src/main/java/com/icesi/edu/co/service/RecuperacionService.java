package com.icesi.edu.co.service;

import com.icesi.edu.co.DTO.ReservationDTO;
import com.icesi.edu.co.repository.OffsetRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecuperacionService {

    @Autowired
    private KafkaConsumer<String, ReservationDTO> consumer;

    @Autowired
    private OffsetRepository offsetRepository;

    public void iniciarProcesamiento() {
        System.out.println("XXXXXXXXXXXXXXXX");
        consumer.subscribe(Arrays.asList("class-reservation"));

        Map<TopicPartition, Long> ultimoOffsetProcesado = cargarUltimoOffset();
        for (Map.Entry<TopicPartition, Long> entry : ultimoOffsetProcesado.entrySet()) {
            consumer.seek(entry.getKey(), entry.getValue());
        }

        while (true) {
            ConsumerRecords<String, ReservationDTO> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, ReservationDTO> record : records) {
                procesarRecord(record);
                guardarOffset(record.topic(), record.partition(), record.offset());
            }
        }
    }

    private Map<TopicPartition, Long> cargarUltimoOffset() {
        return offsetRepository.getLastOffsets();
    }

    private void procesarRecord(ConsumerRecord<String, ReservationDTO> record) {
        System.out.println("Procesando reserva: " + record.value());
    }

    private void guardarOffset(String topic, int partition, long offset) {
        offsetRepository.saveOffset(topic, partition, offset);
    }
}
