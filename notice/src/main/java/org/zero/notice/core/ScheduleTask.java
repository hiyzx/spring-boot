package org.zero.notice.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2018/08/03
 */
@Configuration
@Slf4j
public class ScheduleTask {

    @Resource
    private HttpClient feiGeHttpClient;

    @Scheduled(cron = "0 30 9 ? * MON ")
    public void sendNoticeMorning() throws IOException {
        sendNotice();
        log.info("notice for morning success");
    }

    @Scheduled(cron = "0 0 18 ? * FRI ")
    public void sendNoticeAfternoon() throws IOException {
        sendNotice();
        log.info("notice for afternoon success");
    }


    private void sendNotice() throws IOException {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", "7d55aa74dbae1c1c0bc68453e3e7742a");
        params.put("uid", "284");
        params.put("key", "notice");
        params.put("title", "周报提醒通知！");
        params.put("content", "该提交周报了！");
        params.put("remark", "");
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }
}
