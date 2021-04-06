package com.endava.neo4jworkshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.endava.neo4jworkshop")
public class Neo4jWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jWorkshopApplication.class, args);
    }
}
