package com.icesi.edu.co.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import org.apache.kafka.common.TopicPartition;

@Repository
public class OffsetRepository {

    // Simulación de una base de datos
    private Map<TopicPartition, Long> offsetStore;

    public Map<TopicPartition, Long> getLastOffsets() {
        // Lógica para recuperar offsets desde la base de datos
        return offsetStore; // Asegúrate de retornar los offsets correctos
    }

    public void saveOffset(String topic, int partition, long offset) {
        // Lógica para guardar el offset en la base de datos
        TopicPartition topicPartition = new TopicPartition(topic, partition);
        offsetStore.put(topicPartition, offset);
    }
}
