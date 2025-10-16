package dev.gabrielt.microservices.authentication_service.unitTests.status;

import dev.gabrielt.microservices.authentication_service.config.TestsConfigs;
import dev.gabrielt.microservices.authentication_service.dto.StatusDTO;
import dev.gabrielt.microservices.authentication_service.repository.StatusRepository;
import dev.gabrielt.microservices.authentication_service.service.StatusService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceStatusTest {
    @InjectMocks
    private StatusService statusService;

    @Mock
    private StatusRepository statusRepository;

	@Test
    @DisplayName("status should contain database version equals 16.10")
	void statusShouldContainDatabaseVersion() {
        Mockito.when(statusRepository.getDatabaseVersion()).thenReturn("16.10");
        StatusDTO status = statusService.status();

        Assertions.assertNotNull(status);
        Assertions.assertEquals("16.10", status.dependencies().database().databaseVersion());
	}

    @Test
    @DisplayName("status should contain database max connections equals 100")
    void statusShouldContainDatabaseMaxConnections() {
        Mockito.when(statusRepository.getDatabaseMaxConnections()).thenReturn("100");
        StatusDTO status = statusService.status();

        Assertions.assertNotNull(status);
        Assertions.assertEquals("100", status.dependencies().database().connections().max());
    }

    @Test
    @DisplayName("status should contain database opened connections equals 10")
    void statusShouldContainDatabaseOpenedConnections() {
        Mockito.when(statusRepository.getDatabaseOpenedConnections()).thenReturn("10");
        StatusDTO status = statusService.status();

        Assertions.assertNotNull(status);
        Assertions.assertEquals("10", status.dependencies().database().connections().open());
    }

}
