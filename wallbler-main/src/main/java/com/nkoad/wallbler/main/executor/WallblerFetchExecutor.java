package com.nkoad.wallbler.main.executor;

import com.nkoad.wallbler.main.model.WallblerExecutorScheduler;
import com.nkoad.wallbler.main.repository.WallblerExecutorSchedulerRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WallblerFetchExecutor extends WallblerExecutor {

    private final WallblerExecutorSchedulerRepository feedRepository;
    private final RabbitTemplate executorRabbitTemplate;

    @Scheduled(cron = "0 * * ? * *")
    public void schedule() {
        feedRepository
                .findAll()
                .stream()
                .filter(WallblerExecutorScheduler::isEnable)
                .filter(x -> x.getWallblerNames() != null && !x.getWallblerNames().isEmpty())
                .filter(x -> isTimeToExecute(x.getLastTimeFetched(), x.getPeriod()))
                .forEach(this::executeFetch);
    }

    private void executeFetch(WallblerExecutorScheduler scheduler) {
        Optional.ofNullable(scheduler.getWallblerNames()).ifPresent(wallblerAccountNames ->
                Arrays.stream(wallblerAccountNames.split("\\|"))
                        .filter(wallblerAccountName -> !wallblerAccountName.isEmpty())
                        .forEach(wallblerAccountName -> {
                            String wallblerType = wallblerAccountName.split("::")[0];
                            String accountName = wallblerAccountName.split("::")[1];
                            executorRabbitTemplate
                                    .convertAndSend(wallblerType.toLowerCase() + "-executor-exchange",
                                            wallblerType.toLowerCase() + "-executor",
                                            accountName,
                                            getMessagePostProcessor(wallblerType,
                                                    () -> feedRepository.updateLastTimeFetched(new Date(),
                                                            scheduler.getSchedulerName())));
                        }));
    }

}
