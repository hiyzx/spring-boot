package org.zero.hessian.client.config;

import com.caucho.hessian.client.HessianProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zero.hessian.server.inf.IWeatherService;

import java.net.MalformedURLException;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@Configuration
public class HessianConfig {


    @Bean
    public IWeatherService weatherService() throws MalformedURLException {
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.getSerializerFactory().addFactory(new MyAbstractSerializerFactory());
        return (IWeatherService) factory.create(IWeatherService.class, "http://localhost:8080/weatherServiceHessian");
    }
}
