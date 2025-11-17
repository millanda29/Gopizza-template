package com.gopizza.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(
    value = {
        "com.gopizza.apps",
        "com.gopizza.ordering",
        "com.gopizza.production",
        "com.gopizza.shared"
    }
)

public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

}
