package org.zero.dubbo.client.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.dubbo.api.domain.WeatherVo;
import org.zero.dubbo.api.inf.IWeatherService;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@RestController
public class HelloController {

    @Reference
    private IWeatherService weatherService;

    @GetMapping("/hello")
    public WeatherVo hello() {
        return weatherService.queryWeatherByCityName("beijing");
    }

    public static void main(String[] args){
        String str = "123";
        int i = str.indexOf("");
    }
}
