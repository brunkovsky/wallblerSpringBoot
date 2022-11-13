package com.nkoad.wallbler.main.executor;

import com.nkoad.wallbler.main.model.WallblerAccountScheduler;
import com.nkoad.wallbler.main.repository.WallblerSchedulerAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WallblerAccountExecutor extends WallblerExecutor {

    private final WallblerSchedulerAccountRepository accountRepository;
    private final RabbitTemplate executeRabbitTemplate;

    @Scheduled(cron = "0 * * ? * *")
    public void schedule() {
        accountRepository
                .findAll()
                .stream()
                .filter(WallblerAccountScheduler::isEnabled)
                .filter(x -> x.getAccountNames() != null && !x.getAccountNames().isEmpty())
                .filter(x -> isTimeToExecute(x.getLastTimeFetched(), x.getPeriod()))
                .forEach(this::accessTokenRefresh);
    }

    private void accessTokenRefresh(WallblerAccountScheduler scheduler) {
        Optional.ofNullable(scheduler.getAccountNames()).ifPresent(wallblerAccountNames ->
                Arrays.stream(wallblerAccountNames.split("\\|"))
                        .filter(wallblerAccountName -> !wallblerAccountName.isEmpty())
                        .forEach(wallblerAccountName -> {
                            String wallblerType = wallblerAccountName.split("::")[0];
                            String accountName = wallblerAccountName.split("::")[1];
                            executeRabbitTemplate
                                    .convertAndSend(wallblerType.toLowerCase() + "-access-token-exchange",
                                            wallblerType.toLowerCase() + "-access-token",
                                            accountName,
                                            getMessagePostProcessor(wallblerType,
                                                    () -> accountRepository.updateLastTimeFetched(new Date(),
                                                            scheduler.getSchedulerName())));
                        }));

    }

}
