package com.smartcheck.api_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GatewayExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleJwtException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "code", "GATEWAY_401",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}
