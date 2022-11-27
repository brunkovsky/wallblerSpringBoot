package com.nkoad.wallbler.main.executor;

import com.nkoad.wallbler.main.model.WallblerAccessTokenScheduler;
import com.nkoad.wallbler.main.repository.WallblerAccessTokenSchedulerRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WallblerAccessTokenRefresher extends WallblerExecutor {

    private final WallblerAccessTokenSchedulerRepository accountRepository;
    private final RabbitTemplate executorRabbitTemplate;

    @Scheduled(cron = "0 * * ? * *")
    public void schedule() {
        accountRepository
                .findAll()
                .stream()
                .filter(WallblerAccessTokenScheduler::isEnable)
                .filter(x -> x.getWallblerNames() != null && !x.getWallblerNames().isEmpty())
                .filter(x -> isTimeToExecute(x.getLastTimeFetched(), x.getPeriod()))
                .forEach(this::accessTokenRefresh);
    }

    private void accessTokenRefresh(WallblerAccessTokenScheduler scheduler) {
        Optional.ofNullable(scheduler.getWallblerNames()).ifPresent(wallblerAccountNames ->
                Arrays.stream(wallblerAccountNames.split("\\|"))
                        .filter(wallblerAccountName -> !wallblerAccountName.isEmpty())
                        .forEach(wallblerAccountName -> {
                            String wallblerType = wallblerAccountName.split("::")[0];
                            String accountName = wallblerAccountName.split("::")[1];
                            executorRabbitTemplate
                                    .convertAndSend(wallblerType.toLowerCase() + "-access-token-exchange",
                                            wallblerType.toLowerCase() + "-access-token",
                                            accountName,
                                            getMessagePostProcessor(wallblerType,
                                                    () -> accountRepository.updateLastTimeFetched(new Date(),
                                                            scheduler.getSchedulerName())));
                        }));

    }

}
