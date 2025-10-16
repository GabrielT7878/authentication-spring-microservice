package dev.gabrielt.microservices.authentication_service.controller;

import dev.gabrielt.microservices.authentication_service.dto.StatusDTO;
import dev.gabrielt.microservices.authentication_service.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping("/status")
    public StatusDTO status() {
        return statusService.status();
    }
}
