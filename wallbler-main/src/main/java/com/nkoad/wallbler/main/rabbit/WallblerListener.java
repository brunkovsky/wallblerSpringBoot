package com.nkoad.wallbler.main.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkoad.wallbler.main.model.WallblerSchedulerConfigDto;
import com.nkoad.wallbler.main.service.WallblerService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@AllArgsConstructor
public class WallblerListener {

    private final WallblerService wallblerService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = "wallbler-feed-register")
    public void wallblerFeedRegister(String configDto) {
        System.out.println("============");
        WallblerSchedulerConfigDto schedulerConfigDto = objectMapper.readValue(configDto, WallblerSchedulerConfigDto.class);
        System.out.println("received from wallbler-feed-register: " + schedulerConfigDto);
        System.out.println("============");
//        wallblerService.fetch(feedName);
        wallblerService.wallblerFeedRegister(schedulerConfigDto);
    }

}
