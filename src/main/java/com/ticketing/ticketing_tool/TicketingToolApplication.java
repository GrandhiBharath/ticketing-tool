package com.ticketing.ticketing_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ticketing"})
@EntityScan(basePackages = {"com.ticketing.model"})
@EnableJpaRepositories(basePackages = {"com.ticketing.repository"})
public class TicketingToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketingToolApplication.class, args);
    }
}