package com.icesi.edu.co.config;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public StreamsBuilder streamsBuilder() {
        return new StreamsBuilder();
    }

}
