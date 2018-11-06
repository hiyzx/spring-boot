package org.zero.elastic.job.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yezhaoxing
 * @since 2018/11/06
 */
@Component
@ConfigurationProperties(prefix = "elasticJob.secondJob")
public class SecondJobProperties extends CommonProperties {
}
