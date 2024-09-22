package com.icesi.edu.co.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.icesi.edu.co.DTO.ReservationDTO;
import java.time.Duration;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, ReservationDTO> kafkaTemplate(
            ProducerFactory<String, ReservationDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}