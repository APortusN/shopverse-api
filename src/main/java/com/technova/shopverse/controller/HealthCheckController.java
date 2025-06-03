package com.technova.shopverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "HealthCheck", description = "Endpoint para verificar el estado de la API")
public class HealthCheckController {

    @Operation(
            summary = "ping-pong",
            description = "Devuelve 'pong' para verificar que la API esta activa y respondiendo"
    )
    @ApiResponse(responseCode = "200", description = "La API esta activa")
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
