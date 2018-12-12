package org.zero.hessian.server.config;

import com.caucho.hessian.io.SerializerFactory;
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
        HessianServiceExporter exporter = new HessianServiceExporter();
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new MyAbstractSerializerFactory());
        exporter.setSerializerFactory(serializerFactory);
        exporter.setService(weatherService);
        exporter.setServiceInterface(IWeatherService.class);
        return exporter;
    }
}
