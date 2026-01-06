package com.smartcheck.api_gateway.filter;

import io.jsonwebtoken.Claims;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtTokenValidator jwtTokenValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            Claims claims = jwtTokenValidator.validateAndGetClaims(token);

            String username = claims.getSubject();
            String roles = claims.get("roles", String.class);

            // Pass data to downstream services via headers
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .header("X-User-Name", username)
                    .header("X-User-Roles", roles)
                    .build();

            exchange = exchange.mutate()
                    .request(modifiedRequest)
                    .build();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // run early
    }
}
