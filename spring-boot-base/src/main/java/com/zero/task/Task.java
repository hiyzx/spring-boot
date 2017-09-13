package com.zero.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Task {

    @Scheduled(cron = "0 0/1 * * * ?")
    private void updateTeacherPlan() {
        log.info("定时器任务开启");
    }
}
