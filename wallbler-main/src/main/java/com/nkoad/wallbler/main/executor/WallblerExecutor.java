package com.nkoad.wallbler.main.executor;

import com.nkoad.wallbler.main.model.WallblerScheduler;
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
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class WallblerExecutor {

    private final WallblerSchedulerRepository schedulerRepository;
    private final RabbitTemplate executeRabbitTemplate;

    @Scheduled(cron = "0 * * ? * *")
    public void schedule() {
        schedulerRepository
                .findAll()
                .stream()
                .filter(WallblerScheduler::isEnabled)
                .filter(x -> x.getFeedNames() != null && !x.getFeedNames().isEmpty())
                .filter(this::isTimeToExecute)
                .forEach(this::execute);
    }

    private boolean isTimeToExecute(WallblerScheduler scheduler) {
        return Optional.ofNullable(scheduler.getLastTimeFetched())
                .map(x -> x.before(new Date(new Date().getTime() - scheduler.getPeriod() * 60_000L)))
                .orElse(true);
    }

    private void execute(WallblerScheduler scheduler) {
        Optional.ofNullable(scheduler.getFeedNames()).ifPresent(x ->
                Arrays.stream(x.split("\\|")).sequential()
                        .filter(feedName -> !feedName.isEmpty())
                        .forEach(feedName -> executeRabbitTemplate
                                .convertAndSend(generateExchange(scheduler),
                                        generateRoutingKey(scheduler),
                                        feedName,
                                        getMessagePostProcessor(scheduler))));
    }

    private String generateExchange(WallblerScheduler schedulerConfig) {
        return schedulerConfig.getWallblerType().toLowerCase() + "-execute-exchange";
    }

    private String generateRoutingKey(WallblerScheduler schedulerConfig) {
        return schedulerConfig.getWallblerType().toLowerCase() + "-execute";
    }

    private MessagePostProcessor getMessagePostProcessor(WallblerScheduler schedulerConfig) {
        return message -> {
            schedulerRepository.updateLastTimeFetched(new Date(),
                    schedulerConfig.getSchedulerName());
            log.info("Cron executed: {} : {}",
                    schedulerConfig.getWallblerType(),
                    new String(message.getBody(), StandardCharsets.UTF_8));
            message.getMessageProperties().setExpiration("30000");
            return message;
        };
    }

}
