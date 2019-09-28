package com.atpos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AtposApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtposApiApplication.class, args);
    }
}
