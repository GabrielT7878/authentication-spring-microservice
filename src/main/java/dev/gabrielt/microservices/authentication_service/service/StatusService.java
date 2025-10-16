package dev.gabrielt.microservices.authentication_service.service;

import dev.gabrielt.microservices.authentication_service.dto.StatusDTO;
import dev.gabrielt.microservices.authentication_service.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusDTO status(){

        String databaseHealth = statusRepository.getDatabaseHealth();

        String databaseVersion = statusRepository.getDatabaseVersion();
        String databaseMaxConnections = statusRepository.getDatabaseMaxConnections();
        String databaseOpenedConnections = statusRepository.getDatabaseOpenedConnections();

        StatusDTO.Connections connections = StatusDTO.Connections.builder()
                .max(databaseMaxConnections)
                .open(databaseOpenedConnections)
                .build();

        StatusDTO.Database database = StatusDTO.Database.builder()
                .health(databaseHealth)
                .connections(connections)
                .databaseVersion(databaseVersion)
                .build();

        StatusDTO.Dependencies dependencies = StatusDTO.Dependencies.builder().database(database).build();

        return StatusDTO.builder()
                .dependencies(dependencies)
                .build();
    }
}
