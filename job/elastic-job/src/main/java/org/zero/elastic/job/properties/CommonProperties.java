package org.zero.elastic.job.properties;

import lombok.Data;

/**
 * @author yezhaoxing
 * @since 2018/11/06
 */
@Data
public class CommonProperties {

    private String cron;

    private Integer shardingTotalCount;

    private String shardingItemParameters;

    private Boolean failover;
}
