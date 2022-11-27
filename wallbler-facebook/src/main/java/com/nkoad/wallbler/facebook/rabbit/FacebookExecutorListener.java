package com.nkoad.wallbler.facebook.rabbit;

import com.nkoad.wallbler.facebook.service.WallblerService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@AllArgsConstructor
public class FacebookExecutorListener {

    private final WallblerService wallblerService;

    @RabbitListener(queues = "facebook-executor-queue")
    public void facebookExecute(String name) {
        wallblerService.fetch(name);
    }

}
