package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableJpaRepositories(basePackages = {"com.example.restservice.repository"})
@EntityScan(basePackages = {"com.example.restservice.entity"})
public class GreetingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingApplication.class, args);
    }
}