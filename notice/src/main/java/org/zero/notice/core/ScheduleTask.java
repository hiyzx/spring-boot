package org.zero.notice.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.zero.notice.response.AMapResponse;
import org.zero.notice.response.Cast;
import org.zero.notice.response.CiBaResponse;
import org.zero.notice.response.Forecast;

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
    @Resource
    private HttpClient aMapHttpClient;
    @Resource
    private HttpClient ciBaHttpClient;

    @Scheduled(cron = "0 30 17 ? * FRI ")
    public void sendNoticeAfternoon() throws IOException {
        sendWeekReportNotice();
        log.info("notice for afternoon success");
    }

    @Scheduled(cron = "0 0 7 * * * ")
    public void sendNoticeWeather() throws IOException {
        Map<String, String> params = new HashMap<>(3);
        params.put("key", "4d3581ba3666ba2b1d8537f3d02d2ca0");
        params.put("city", "440300");
        params.put("extensions", "all");
        String weatherResponse = aMapHttpClient.get("/v3/weather/weatherInfo", params);
        String ciBaResponse = ciBaHttpClient.get("/dsapi");
        sendWeatherNotice(JsonUtil.readValue(weatherResponse, AMapResponse.class),
                JsonUtil.readValue(ciBaResponse, CiBaResponse.class));
        log.info("notice weather success");
    }

    private void sendWeekReportNotice() throws IOException {
        sendNotice("周报提醒通知！", "该提交周报了！", null);
    }

    private void sendWeatherNotice(AMapResponse aMapResponse, CiBaResponse ciBaResponse) throws IOException {
        Forecast forecast = aMapResponse.getForecasts().get(0);

        String remark = String
                .format("\n%s\n%s\n\n%s天气：", ciBaResponse.getContent(), ciBaResponse.getNote(), forecast.getCity());

        for (Cast cast : forecast.getCasts()) {
            remark = remark + String.format("\n%s：白天{%s}，温度{%s}℃ ~ {%s}℃。", cast.getDate(), cast.getDayweather(),
                    cast.getNighttemp(), cast.getDaytemp());
        }

        sendNotice("早上好！", "每日心灵鸡汤", remark);
    }

    private void sendNotice(String title, String content, String remark) throws IOException {
        Map<String, String> params = new HashMap<>(5);
        params.put("secret", "7d55aa74dbae1c1c0bc68453e3e7742a");
        params.put("uid", "284");
        params.put("key", "notice");
        params.put("title", title);
        params.put("content", content);
        params.put("remark", remark);
        feiGeHttpClient.post("/api/user_sendmsg", params);
    }
}
