package org.zero.dubbo.server.impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.zero.dubbo.api.domain.WeatherVo;
import org.zero.dubbo.api.inf.IWeatherService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @since 2017/08/09
 */
@Component
@Service(timeout = 5000)
public class WeatherServiceImpl implements IWeatherService {

    private static final Map<String, WeatherVo> weatherMap = new HashMap<>();

    static {
        DateTime now = DateUtil.date();
        weatherMap.put("beijing", new WeatherVo(1L, "北京", "30摄氏度", "雷阵雨", now, new BigDecimal(1)));
        weatherMap
                .put("shanghai", new WeatherVo(2L, "上海", "20摄氏度", "晴天", DateUtil.offsetDay(now, 1), new BigDecimal(1)));
        weatherMap.put("shenzhen",
                new WeatherVo(3L, "深圳", "25摄氏度", "大太阳", DateUtil.offsetDay(now, 2), new BigDecimal(1)));
    }

    @Override
    public WeatherVo queryWeatherByCityName(String cityName) {
        System.out.println("hello");
        return weatherMap.get(cityName);
    }
}
