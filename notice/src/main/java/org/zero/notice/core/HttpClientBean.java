package org.zero.notice.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yezhaoxing
 * @since 2018/08/03
 */
@Configuration
public class HttpClientBean {

    @Bean("feiGeHttpClient")
    public HttpClient feiGeHttpClient() {
        return new HttpClient("http", "u.ifeige.cn", 80);
    }

    @Bean("juHeHttpClient")
    public HttpClient juHeHttpClient() {
        return new HttpClient("http", "apis.juhe.cn", 80);
    }
}
