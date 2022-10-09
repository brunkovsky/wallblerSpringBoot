package com.nkoad.wallbler.main.executor;

import com.nkoad.wallbler.main.repository.WallblerSchedulerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WallblerExecutor {

    private final WallblerSchedulerRepository schedulerRepository;
    private final RabbitTemplate commonTemplate;
//
//    @Scheduled(cron = "0 * * ? * *")
//    public void schedule() {
//        schedulerRepository
//                .findAll()
//                .stream()
//                .filter(this::isTimeToExecute)
//                .forEach(this::execute);
//    }
//
//    private boolean isTimeToExecute(WallblerScheduler scheduler) {
//        Date lastTimeFetched = scheduler.getLastTimeFetched();
//        Date newTimeFetched = new Date(new Date().getTime() - scheduler.getPeriod() * 60_000L);
//        return lastTimeFetched.before(newTimeFetched);
//    }
//
//    private void execute(WallblerScheduler scheduler) {
//        commonTemplate.convertAndSend(generateExchange(scheduler),
//                generateRoutingKey(scheduler),
//                scheduler.getFeedName(),
//                getMessagePostProcessor(scheduler));
//    }
//
//    private String generateExchange(WallblerScheduler schedulerConfig) {
//        return schedulerConfig.getWallblerType().toLowerCase() + "-execute-exchange";
//    }
//
//    private String generateRoutingKey(WallblerScheduler schedulerConfig) {
//        return schedulerConfig.getWallblerType().toLowerCase() + "-execute";
//    }
//
//    private MessagePostProcessor getMessagePostProcessor(WallblerScheduler schedulerConfig) {
//        return message -> {
//            schedulerRepository.updateLastTimeFetched(new Date(), schedulerConfig.getFeedName());
//            System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
//            log.info("cron executed: {}, {}", schedulerConfig.getWallblerType(), schedulerConfig.getFeedName());
//            return message;
//        };
//    }

}
