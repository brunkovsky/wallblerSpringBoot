package com.nkoad.wallbler.facebook.startup;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class WallblerTypeRegister {

//    @Value("${wallbler.type}")
//    String wallblerType;

    private final RabbitTemplate registerTypeTemplate;


    @PostConstruct
    public void registerWallblerType() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            registerTypeTemplate.convertAndSend("FACEBOOK");
        }).start();

    }

}
