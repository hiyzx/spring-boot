package org.zero.notice.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zero.notice.core.NoticeUtil;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @since 2018/08/03
 */
@Component
@Slf4j
public class WeekReportTask {

    @Resource
    private NoticeUtil noticeUtil;

    @Scheduled(cron = "0 30 09 ? * FRI ")
    public void sendNoticeAfternoon() throws IOException {
        noticeUtil.sendNotice("周报提醒通知！", "该提交周报了！", null, 284);
        log.info("notice for week report success");
    }
}
