package com.nkoad.wallbler.main.executor;

import com.nkoad.wallbler.main.model.WallblerSchedulerConfig;
import com.nkoad.wallbler.main.repository.WallblerSchedulerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class Executor {

    private final WallblerSchedulerRepository schedulerRepository;
    private final RabbitTemplate commonTemplate;

    @Scheduled(cron = "0 * * ? * *")
    public void schedule() {
        schedulerRepository
                .findAll()
                .stream()
                .filter(this::isTimeToExecute)
                .forEach(this::execute);
    }

    private boolean isTimeToExecute(WallblerSchedulerConfig schedulerConfig) {
        Date lastTimeFetched = schedulerConfig.getLastTimeFetched();
        Date newTimeFetched = new Date(new Date().getTime() - schedulerConfig.getPeriod() * 60_000L);
        return lastTimeFetched.before(newTimeFetched);
    }

    private void execute(WallblerSchedulerConfig schedulerConfig) {
        commonTemplate.convertAndSend(generateExchange(schedulerConfig),
                generateRoutingKey(schedulerConfig),
                schedulerConfig.getFeedName(),
                getMessagePostProcessor(schedulerConfig));
    }

    private String generateExchange(WallblerSchedulerConfig schedulerConfig) {
        return schedulerConfig.getWallblerType().toLowerCase() + "-execute-exchange";
    }

    private String generateRoutingKey(WallblerSchedulerConfig schedulerConfig) {
        return schedulerConfig.getWallblerType().toLowerCase() + "-execute";
    }

    private MessagePostProcessor getMessagePostProcessor(WallblerSchedulerConfig schedulerConfig) {
        return message -> {
            schedulerRepository.updateLastTimeFetched(new Date(), schedulerConfig.getFeedName());
            System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
            log.info("cron executed: {}, {}", schedulerConfig.getWallblerType(), schedulerConfig.getFeedName());
            return message;
        };
    }

}
