package org.zero.entertainment.core;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2018/09/04
 */
@Configuration
public class AipOcrBean {

    @Resource
    private AppRecognitionProperties appRecognitionProperties;

    @Bean
    public AipOcr aipOcr() {
        AipOcr aipOcr = new AipOcr(appRecognitionProperties.getId(), appRecognitionProperties.getKey(),
                appRecognitionProperties.getSecretKey());
        aipOcr.setConnectionTimeoutInMillis(2000);
        aipOcr.setSocketTimeoutInMillis(60000);
        return aipOcr;
    }

}
