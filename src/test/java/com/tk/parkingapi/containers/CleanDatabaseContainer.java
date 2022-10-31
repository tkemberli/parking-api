package com.tk.parkingapi.containers;


import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

/**
 * Initializes a clean database container to ensure testing purity
 */

public class CleanDatabaseContainer {

    private static final MySQLContainer container = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withInitScript("databaseScripts/basicInit.sql")
            .withDatabaseName("parking")
            .withReuse(true);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeAll
    public static void setup() {
        container.start();
    }
}