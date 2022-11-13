package com.nkoad.wallbler.main.executor;

import com.nkoad.wallbler.main.model.WallblerFeedScheduler;
import com.nkoad.wallbler.main.repository.WallblerSchedulerFeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WallblerFeedExecutor extends WallblerExecutor {

    private final WallblerSchedulerFeedRepository feedRepository;
    private final RabbitTemplate executeRabbitTemplate;

    @Scheduled(cron = "0 * * ? * *")
    public void schedule() {
        feedRepository
                .findAll()
                .stream()
                .filter(WallblerFeedScheduler::isEnabled)
                .filter(x -> x.getFeedNames() != null && !x.getFeedNames().isEmpty())
                .filter(x -> isTimeToExecute(x.getLastTimeFetched(), x.getPeriod()))
                .forEach(this::feedExecute);
    }

    private void feedExecute(WallblerFeedScheduler scheduler) {
        Optional.ofNullable(scheduler.getFeedNames()).ifPresent(wallblerAccountNames ->
                Arrays.stream(wallblerAccountNames.split("\\|"))
                        .filter(wallblerAccountName -> !wallblerAccountName.isEmpty())
                        .forEach(wallblerAccountName -> {
                            String wallblerType = wallblerAccountName.split("::")[0];
                            String accountName = wallblerAccountName.split("::")[1];
                            executeRabbitTemplate
                                    .convertAndSend(wallblerType.toLowerCase() + "-execute-exchange",
                                            wallblerType.toLowerCase() + "-execute",
                                            accountName,
                                            getMessagePostProcessor(wallblerType,
                                                    () -> feedRepository.updateLastTimeFetched(new Date(),
                                                            scheduler.getSchedulerName())));
                        }));

    }

}
