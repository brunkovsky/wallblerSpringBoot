package com.nkoad.wallbler.main.rabbit;

import com.nkoad.wallbler.main.service.WallblerTypeService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@AllArgsConstructor
public class WallblerListener {

    private final WallblerTypeService service;

    @RabbitListener(queues = "wallbler-type-register")
    public void wallblerFeedRegister(String wabblerTypeName) {
        service.registerWallblerType(wabblerTypeName);
    }

}
