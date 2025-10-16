package dev.gabrielt.microservices.authentication_service.dto;

import lombok.Builder;

@Builder
public record StatusDTO(Dependencies dependencies) {
    @Builder
    public record Database(String health,String databaseVersion, Connections connections){ }

    @Builder
    public record Dependencies(Database database){}

    @Builder
    public record Connections(String open, String max){}
}



