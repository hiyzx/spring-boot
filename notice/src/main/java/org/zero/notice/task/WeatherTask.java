package org.zero.notice.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zero.notice.core.AMapUtil;
import org.zero.notice.core.HttpClient;
import org.zero.notice.core.JsonUtil;
import org.zero.notice.core.NoticeUtil;
import org.zero.notice.response.CiBaResponse;
import org.zero.notice.response.FeiGeListUserInfoVo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author yezhaoxing
 * @since 2018/08/03
 */
@Component
@Slf4j
public class WeatherTask {

    @Resource
    private NoticeUtil noticeUtil;
    @Resource
    private AMapUtil aMapUtil;
    @Resource
    private HttpClient ciBaHttpClient;

    @Scheduled(cron = "0 30 6 * * * ")
    public void sendNoticeWeatherMorningQZ() throws IOException {
        List<FeiGeListUserInfoVo> userInfoVos = noticeUtil.list();
        for (FeiGeListUserInfoVo user : userInfoVos) {
            if (user.getId().equals(284)) {
                // 湖里
                noticeUtil.sendNotice(String.format("%s：早上好！", user.getRemark()), "天气预报", aMapUtil.getWeather("350206"),
                    user.getId());
            }
            if (user.getId().equals(2885)) {
                // 安溪
                noticeUtil.sendNotice(String.format("%s：早上好！", user.getRemark()), "天气预报", aMapUtil.getWeather("350524"),
                    user.getId());
            }
        }
        log.info("notice weather success");
    }

    @Scheduled(cron = "0 0 23 * * * ")
    public void sendNoticeNight() throws IOException {
        List<FeiGeListUserInfoVo> userInfoVos = noticeUtil.list();
        for (FeiGeListUserInfoVo user : userInfoVos) {
            CiBaResponse ciBaResponse = JsonUtil.readValue(ciBaHttpClient.get("/dsapi"), CiBaResponse.class);
            String content = String.format("\n%s\n%s", ciBaResponse.getContent(), ciBaResponse.getNote());
            noticeUtil.sendNotice(String.format("%s：晚上好！", user.getRemark()), "每日心灵鸡汤", content, user.getId());
            log.info("notice night success");
        }
    }
}
