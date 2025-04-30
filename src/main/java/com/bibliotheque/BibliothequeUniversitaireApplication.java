package com.bibliotheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bibliotheque.repository") 
@EntityScan(basePackages = "com.bibliotheque.model") 
public class BibliothequeUniversitaireApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliothequeUniversitaireApplication.class, args);
    }
}