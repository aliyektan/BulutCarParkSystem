package com.aliyektan.bulut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BulutApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulutApplication.class, args);
    }

}
