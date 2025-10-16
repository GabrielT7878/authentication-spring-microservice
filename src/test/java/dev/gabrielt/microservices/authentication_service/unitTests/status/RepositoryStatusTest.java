package dev.gabrielt.microservices.authentication_service.unitTests.status;

import dev.gabrielt.microservices.authentication_service.repository.StatusRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class RepositoryStatusTest {

    @Spy
    @InjectMocks
    StatusRepository statusRepository;

    @Mock
    EntityManager entityManager;

    @Test
    @DisplayName("getDatabaseHealth should return 'Unhealthy' on exception")
    void statusShouldReturnUnhealthyOnException() {
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString()))
                .thenThrow(new RuntimeException());

        String databaseHealth = statusRepository.getDatabaseHealth();

        Assertions.assertNotNull(databaseHealth);
        Assertions.assertEquals("Unhealthy", databaseHealth);
    }

    @Test
    @DisplayName("getDatabaseVersion should return 'Unavailable' on exception")
    void getDatabaseVersionShouldReturnUnavailableOnException() {
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString()))
                .thenThrow(new RuntimeException());

        String databaseVersion = statusRepository.getDatabaseVersion();

        Assertions.assertEquals("Unavailable", databaseVersion);
    }

    @Test
    @DisplayName("getDatabaseMaxConnections should return 'Unavailable' on exception")
    void getDatabaseMaxConnectionsShouldReturnUnavailableOnException() {
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString()))
                .thenThrow(new RuntimeException());

        String maxConnections = statusRepository.getDatabaseMaxConnections();

        Assertions.assertEquals("Unavailable", maxConnections);
    }

    @Test
    @DisplayName("getDatabaseOpenedConnections should return 'Unavailable' on exception")
    void getDatabaseOpenedConnectionsShouldReturnUnavailableOnException() {
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString()))
                .thenThrow(new RuntimeException());

        String openedConnections = statusRepository.getDatabaseOpenedConnections();

        Assertions.assertEquals("Unavailable", openedConnections);
    }

}
