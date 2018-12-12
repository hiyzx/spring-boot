package org.zero.hessian.server.inf;


import org.zero.hessian.server.domain.WeatherVo;

/**
 * @author zero
 * @since 2017/08/09
 */
public interface IWeatherService {

    WeatherVo queryWeatherByCityName(String cityName);
}
