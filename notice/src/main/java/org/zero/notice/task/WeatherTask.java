package org.zero.notice.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zero.notice.core.HttpClient;
import org.zero.notice.core.JsonUtil;
import org.zero.notice.core.NoticeUtil;
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
@Component
@Slf4j
public class WeatherTask {

    @Resource
    private NoticeUtil noticeUtil;
    @Resource
    private HttpClient aMapHttpClient;
    @Resource
    private HttpClient ciBaHttpClient;

    @Scheduled(cron = "0 0 7 * * * ")
    public void sendNoticeWeatherMorning() throws IOException {
        Map<String, String> params = new HashMap<>(3);
        params.put("key", "4d3581ba3666ba2b1d8537f3d02d2ca0");
        params.put("city", "440300");
        params.put("extensions", "all");
        String weatherResponse = aMapHttpClient.get("/v3/weather/weatherInfo", params);
        Forecast forecast = JsonUtil.readValue(weatherResponse, AMapResponse.class).getForecasts().get(0);
        String remark = String.format("\n%s天气：", forecast.getCity());
        for (Cast cast : forecast.getCasts()) {
            remark = remark + String
                    .format("\n%s：白天{%s}，温度{%s}℃ ~ {%s}℃。", cast.getDate(), cast.getDayweather(), cast.getNighttemp(),
                            cast.getDaytemp());
        }
        noticeUtil.sendNotice("早上好！", "天气预报", remark);
        log.info("notice weather success");
    }

    @Scheduled(cron = "0 0 23 * * * ")
    public void sendNoticeNight() throws IOException {
        CiBaResponse ciBaResponse = JsonUtil.readValue(ciBaHttpClient.get("/dsapi"), CiBaResponse.class);
        String content = String.format("\n%s\n%s", ciBaResponse.getContent(), ciBaResponse.getNote());
        noticeUtil.sendNotice("晚上好！", "每日心灵鸡汤", content);
        log.info("notice night success");
    }
}
