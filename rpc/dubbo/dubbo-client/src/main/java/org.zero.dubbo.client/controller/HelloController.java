package org.zero.dubbo.client.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.dubbo.api.domain.WeatherVo;
import org.zero.dubbo.api.inf.IWeatherService;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@RestController
public class HelloController {

    @Reference(version = "2.0", filter = "methodFilter")
    private IWeatherService weatherService;

    @GetMapping("/hello")
    public WeatherVo hello() {
        WeatherVo weatherVo = weatherService.queryWeatherByCityName("beijing");
        return weatherVo;
    }

    @GetMapping("/random")
    public WeatherVo hello(@RequestParam Integer index) {
        WeatherVo weatherVo = weatherService.queryRandom(index);
        return weatherVo;
    }
}
