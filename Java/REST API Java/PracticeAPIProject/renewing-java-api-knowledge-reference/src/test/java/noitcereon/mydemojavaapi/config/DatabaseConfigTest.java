package noitcereon.mydemojavaapi.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseConfigTest {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Test
    void given_uri_when_methodIsHit_then_generateValidJdbcUri() {
        // TODO: write test for valid jdbc url
    }

    // Fragile test (if the application doesn't run, it fails since it is a SpringBootTest)
    @Test
    void given_environmentVariableFromSystem_when_methodIsHit_then_variableFromSystemIsPrioritizedOverEquivalentDotEnvAndDatabaseUrlIsNotNull() {
        String databaseUrlFromSystemEnvironment = System.getenv("DATABASE_URL");

        assertNotNull(databaseUrl);
        if (databaseUrlFromSystemEnvironment != null) {
            assertEquals(databaseUrlFromSystemEnvironment, databaseUrl);
        }
    }
}