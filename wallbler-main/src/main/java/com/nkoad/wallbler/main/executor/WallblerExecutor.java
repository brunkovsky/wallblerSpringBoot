package com.nkoad.wallbler.main.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Slf4j
public abstract class WallblerExecutor {

    protected boolean isTimeToExecute(Date lastDateFetched, int period) {
        return Optional.ofNullable(lastDateFetched)
                .map(x -> x.before(new Date(new Date().getTime() - period * 60_000L)))
                .orElse(true);
    }

    protected MessagePostProcessor getMessagePostProcessor(String wallblerType, Runnable runnable) {
        return message -> {
            runnable.run();
            log.info("Cron executed. Wallbler type: {}. Name: {}",
                    wallblerType,
                    new String(message.getBody(), StandardCharsets.UTF_8));
            message.getMessageProperties().setExpiration("30000");
            return message;
        };
    }

}
