package org.zero.elastic.job.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yezhaoxing
 * @since 2018/11/06
 */
@Component
@ConfigurationProperties(prefix = "elasticJob.regCenter")
@Data
public class RegistryCenterProperties {

    private String serverList;

    private String namespace;
}
