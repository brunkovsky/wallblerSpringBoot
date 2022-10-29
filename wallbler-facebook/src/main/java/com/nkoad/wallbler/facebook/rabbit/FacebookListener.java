package com.nkoad.wallbler.facebook.rabbit;

import com.nkoad.wallbler.facebook.service.WallblerService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@AllArgsConstructor
public class FacebookListener {

    private final WallblerService wallblerService;


    @RabbitListener(queues = "facebook-execute-queue")
    public void facebookExecute(String feedName) {
        System.out.println("received from facebook-execute-queue: " + feedName);
        wallblerService.fetch(feedName);
    }

}
