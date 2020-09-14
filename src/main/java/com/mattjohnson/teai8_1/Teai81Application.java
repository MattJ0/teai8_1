package com.mattjohnson.teai8_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Teai81Application {

    public static void main(String[] args) {
        SpringApplication.run(Teai81Application.class, args);

    }

}
