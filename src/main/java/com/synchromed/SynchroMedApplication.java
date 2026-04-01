package com.synchromed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// Excluimos la base de datos momentáneamente para que arranque sin necesidad de conectar MySQL aún
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SynchroMedApplication {
    public static void main(String[] args) {
        SpringApplication.run(SynchroMedApplication.class, args);
    }
}
