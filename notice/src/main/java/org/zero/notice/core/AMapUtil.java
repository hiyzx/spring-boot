package org.zero.notice.core;

import org.springframework.stereotype.Component;
import org.zero.notice.response.AMapResponse;
import org.zero.notice.response.Cast;
import org.zero.notice.response.Forecast;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2019/12/10
 */
@Component
public class AMapUtil {

    @Resource
    private HttpClient aMapHttpClient;

    public String getWeather(String cityCode) {
        Map<String, String> params = new HashMap<>(3);
        params.put("key", "4d3581ba3666ba2b1d8537f3d02d2ca0");
        params.put("city", cityCode);
        params.put("extensions", "all");
        String weatherResponse = aMapHttpClient.get("/v3/weather/weatherInfo", params);
        Forecast forecast = JsonUtil.readValue(weatherResponse, AMapResponse.class).getForecasts().get(0);
        String remark = String.format("\n%s天气：", forecast.getCity());
        for (Cast cast : forecast.getCasts()) {
            remark = remark + String.format("\n%s：白天{%s}，温度{%s}℃ ~ {%s}℃。", cast.getDate(), cast.getDayweather(),
                cast.getNighttemp(), cast.getDaytemp());
        }
        return remark;
    }
}
