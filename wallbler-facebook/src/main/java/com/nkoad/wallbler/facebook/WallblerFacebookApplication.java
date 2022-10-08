package com.nkoad.wallbler.facebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WallblerFacebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(WallblerFacebookApplication.class, args);
    }

}
