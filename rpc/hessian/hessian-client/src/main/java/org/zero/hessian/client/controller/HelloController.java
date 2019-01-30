package org.zero.hessian.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.hessian.server.domain.WeatherVo;
import org.zero.hessian.server.inf.IWeatherService;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@RestController
public class HelloController {

    @Autowired
    private IWeatherService weatherService;

    @GetMapping("/hello")
    public WeatherVo hello() {
        return weatherService.queryWeatherByCityName("beijing");
    }
}
