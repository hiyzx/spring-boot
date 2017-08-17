package com.zero.vo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * http请求的信息,将配置文件的信息注入
 * 
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Component
@ConfigurationProperties(prefix = "http")
@Data
public class HttpInfoProperties {

    private String scheme;// 协议
    private String hostname;// 域名
    private String port;// 端口
}
