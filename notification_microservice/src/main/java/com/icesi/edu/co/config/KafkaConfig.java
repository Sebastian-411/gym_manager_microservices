package com.icesi.edu.co.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.icesi.edu.co.DTO.ReservationDTO;
import com.icesi.edu.co.model.ResumeTraining;

import java.time.Duration;
import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, ReservationDTO> kafkaTemplate(
            ProducerFactory<String, ReservationDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KStream<String, ReservationDTO> kStream(StreamsBuilder streamsBuilder) {
        System.out.println("Iniciando el KStream...");
        KStream<String, ReservationDTO> stream = streamsBuilder.stream("class-reservation");

        stream.foreach((key, value) -> {
            System.out.println("Recibido mensaje: " + value);
        });

        stream.groupBy(
                (key, value) -> {
                    value.getMemberId().toString();
                    System.out.println("Agrupando por: " + value.getMemberId().toString());
                    return value.getMemberId().toString();
                })
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofDays(7)))
                .aggregate(
                        () -> new ResumeTraining(),
                        (memberId, reservation, resumen) -> {
                            System.out.println("Agregando reserva: " + reservation);
                            return resumen.updateBooking(reservation);
                        },
                        Materialized.as("resume-training-store"))
                .toStream()
                .to("resumen-entrenamiento");

        return stream;
    }

    @Bean
    public KafkaConsumer<String, ReservationDTO> kafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "class-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new KafkaConsumer<>(props);
    }
}