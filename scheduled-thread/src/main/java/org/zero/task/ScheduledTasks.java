package org.zero.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    // @Scheduled(fixedRate = 5000)
    public void task() {
        log.info("hello-world-task");
    }

}