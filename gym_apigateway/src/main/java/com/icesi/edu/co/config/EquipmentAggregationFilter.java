package com.icesi.edu.co.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.edu.co.DTO.EquipmentDTO;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentAggregationFilter implements GatewayFilter {

    private final WebClient.Builder webClientBuilder;

    
    public EquipmentAggregationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    public Mono<Void> aggregateResponse(ServerWebExchange exchange) {
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        int amount = Integer.parseInt(exchange.getRequest().getQueryParams().getFirst("amount"));

        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8087/api/gym/equipment")
                .header(HttpHeaders.AUTHORIZATION,
                        exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .retrieve()
                .bodyToMono(EquipmentDTO[].class)
                .flatMap(equipments -> {
                    try {

                        List<EquipmentDTO> filteredEquipments = filterEquipments(equipments, name, amount);

                        String responseBody;
                        responseBody = new ObjectMapper().writeValueAsString(filteredEquipments);

                        return exchange.getResponse().writeWith(Mono.just(
                                exchange.getResponse().bufferFactory().wrap(responseBody.getBytes())));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return Mono.error(e);
                    }

                });
    }

    private List<EquipmentDTO> filterEquipments(EquipmentDTO[] equipments, String name, int amount) {
        return Arrays.stream(equipments)
                .filter(e -> e.getName().equalsIgnoreCase(name) && e.getAmount() >= amount)
                .collect(Collectors.toList());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getPath().value().equals("/api/gym/equipments/search")) {
            return aggregateResponse(exchange); 
        }
        return chain.filter(exchange);

    }
}
