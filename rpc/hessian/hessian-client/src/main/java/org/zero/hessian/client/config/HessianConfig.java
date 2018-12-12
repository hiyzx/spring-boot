package org.zero.hessian.client.config;

import com.caucho.hessian.client.HessianProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zero.hessian.server.config.MyAbstractSerializerFactory;
import org.zero.hessian.server.inf.IWeatherService;

import java.net.MalformedURLException;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@Configuration
public class HessianConfig {

    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String WEATHER_SERVICE_URL = "http://localhost:8080/weatherServiceHessian";


    @Bean
    public IWeatherService weatherService() throws MalformedURLException {
        return getService(IWeatherService.class, WEATHER_SERVICE_URL);
    }

    private <T> T getService(Class<T> clazz, String url) throws MalformedURLException {
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setUser(USER);
        factory.setPassword(PASSWORD);
        factory.getSerializerFactory().addFactory(new MyAbstractSerializerFactory());// 重新定义序列化方法,不然BigDecimal有bug
        return (T) factory.create(clazz, url);
    }
}
