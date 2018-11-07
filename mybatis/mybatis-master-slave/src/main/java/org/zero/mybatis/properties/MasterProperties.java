package org.zero.mybatis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yezhaoxing
 * @since 2018/11/07
 */
@Component
@ConfigurationProperties(prefix = "mysql.master")
public class MasterProperties extends CommonProperties {
}
