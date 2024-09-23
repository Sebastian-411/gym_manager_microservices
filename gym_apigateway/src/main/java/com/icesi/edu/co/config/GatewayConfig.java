package com.icesi.edu.co.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Configuration
public class GatewayConfig {

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader != null) {
                return chain.filter(exchange.mutate()
                        .request(request.mutate()
                                .header(HttpHeaders.AUTHORIZATION, authHeader)
                                .build())
                        .build());
            }
            return chain.filter(exchange);
        };
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, EquipmentAggregationFilter equipmentAggregationFilter) {
        return builder.routes()
                .route("equipments-service", r -> r.path("/api/gym/equipments/search")
                .filters(f -> f.filter(equipmentAggregationFilter))
                .uri("no://op"))
                .build();
    }

}