package com.nkoad.wallbler.twitter.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorkHandler {

    @StreamListener(WorkUnitsSink.CHANNEL_NAME)
    public void process(WorkUnit workUnit) {
        log.info("Handling work unit - id: {}, definition: {}", workUnit.getId(), workUnit.getDefinition());
    }

}
