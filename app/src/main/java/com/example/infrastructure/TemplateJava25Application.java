package com.example.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class TemplateJava25Application {

    public static void main(String[] args) {
        SpringApplication.run(TemplateJava25Application.class, args);
    }
}
