package com.zero.bean;

import com.zero.util.HttpClient;
import com.zero.vo.properties.HttpInfoProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * http bean对象
 *
 * @author yezhaoxing
 * @since 2017/7/17
 */
@Configuration
@Data
public class HttpClientBean {

    @Resource
    private HttpInfoProperties httpInfoProperties;

    @Bean("localHttpClient")
    public HttpClient localHttpClient() {
        return new HttpClient(httpInfoProperties.getScheme(), httpInfoProperties.getHostname(),
                Integer.valueOf(httpInfoProperties.getPort()));
    }
}
