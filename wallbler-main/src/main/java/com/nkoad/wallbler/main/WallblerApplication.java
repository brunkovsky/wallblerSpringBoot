package com.nkoad.wallbler.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WallblerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WallblerApplication.class, args);
    }

}
