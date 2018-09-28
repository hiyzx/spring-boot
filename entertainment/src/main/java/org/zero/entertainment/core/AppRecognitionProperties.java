package org.zero.entertainment.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yezhaoxing
 * @since 2018/09/04
 */
@Component
@ConfigurationProperties(prefix = "app.recognition")
@Data
public class AppRecognitionProperties {

    private String id;

    private String key;

    private String secretKey;
}
