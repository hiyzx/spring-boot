package org.zero.hessian.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.zero.hessian.server.inf.IWeatherService;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@Configuration
public class HessianConfig {

    @Resource
    private IWeatherService weatherService;

    @Bean(name = "/weatherServiceHessian")
    public HessianServiceExporter weatherService() {
        CustomerHessianServiceExporter exporter = CustomerHessianServiceExporter.instance();
        exporter.setService(weatherService);
        exporter.setServiceInterface(IWeatherService.class);
        return exporter;
    }
}
