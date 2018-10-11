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

    @Bean("aMapHttpClient")
    public HttpClient aMapHttpClient() {
        return new HttpClient("https", "restapi.amap.com", 443);
    }

    @Bean("ciBaHttpClient")
    public HttpClient juHeHttpClient() {
        return new HttpClient("http", "open.iciba.com", 80);
    }
}
