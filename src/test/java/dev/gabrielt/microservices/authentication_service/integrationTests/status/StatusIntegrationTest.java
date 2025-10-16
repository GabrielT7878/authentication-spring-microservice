package dev.gabrielt.microservices.authentication_service.integrationTests.status;

import dev.gabrielt.microservices.authentication_service.config.TestsConfigs;
import dev.gabrielt.microservices.authentication_service.integrationTests.testcontainers.AbstractIntegrationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StatusIntegrationTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("status should contain database version equals 16.10")
    void statusShouldContainDatabaseVersion() {
        Object databaseVersion = (String) RestAssured.given()
                .basePath("/status")
                .port(TestsConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body().path("dependencies.database.databaseVersion");

        Assertions.assertEquals("16.10", databaseVersion);

    }

    @Test
    @DisplayName("status should contain database opened connections equals 10")
    void statusShouldContainDatabaseOpenedConnections()  {
        Object databaseOpenedConnections = (String) RestAssured.given()
                .basePath("/status")
                .port(TestsConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body().path("dependencies.database.connections.open");

        Assertions.assertEquals("10", databaseOpenedConnections);

    }

    @Test
    @DisplayName("status should contain database max connections equals 100")
    void statusShouldContainDatabaseMaxConnections() {
        Object databaseMaxConnections = (String) RestAssured.given()
                .basePath("/status")
                .port(TestsConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body().path("dependencies.database.connections.max");

        Assertions.assertEquals("100", databaseMaxConnections);

    }

}
