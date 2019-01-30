package org.zero.dubbo.api.inf;


import org.zero.dubbo.api.domain.WeatherVo;

/**
 * @author zero
 * @since 2017/08/09
 */
public interface IWeatherService {

    WeatherVo queryWeatherByCityName(String cityName);
}
