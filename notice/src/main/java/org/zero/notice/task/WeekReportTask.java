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

    @Scheduled(cron = "0 30 17 ? * FRI ")
    public void sendNoticeAfternoon() throws IOException {
        sendWeekReportNotice();
        log.info("notice for afternoon success");
    }

    private void sendWeekReportNotice() throws IOException {
        noticeUtil.sendNotice("周报提醒通知！", "该提交周报了！", null);
    }
}
