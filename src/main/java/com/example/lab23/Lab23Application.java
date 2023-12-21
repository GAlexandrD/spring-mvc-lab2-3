package com.example.lab23;

import com.example.lab23.Task.utils.DateParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab23Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab23Application.class, args);
    }

    @Bean
    DateParser dateParser() { return new DateParser(); }
}
