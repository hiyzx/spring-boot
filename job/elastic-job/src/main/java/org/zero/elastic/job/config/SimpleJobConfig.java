/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package org.zero.elastic.job.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zero.elastic.job.job.FirstJob;
import org.zero.elastic.job.job.SecondJob;
import org.zero.elastic.job.properties.FirstJobProperties;
import org.zero.elastic.job.properties.SecondJobProperties;

import javax.annotation.Resource;

@Configuration
public class SimpleJobConfig {

    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Resource
    private JobEventConfiguration jobEventConfiguration;

    @Resource
    private FirstJob firstJob;
    @Resource
    private FirstJobProperties firstJobProperties;
    @Resource
    private SecondJob secondJob;
    @Resource
    private SecondJobProperties secondJobProperties;


    @Bean(initMethod = "init")
    public JobScheduler firstJobScheduler() {
        return new SpringJobScheduler(firstJob, regCenter,
                getLiteJobConfiguration(firstJob.getClass(), firstJobProperties.getCron(),
                        firstJobProperties.getShardingTotalCount(), firstJobProperties.getShardingItemParameters()),
                jobEventConfiguration);
    }

    @Bean(initMethod = "init")
    public JobScheduler secondJobScheduler() {
        return new SpringJobScheduler(secondJob, regCenter,
                getLiteJobConfiguration(secondJob.getClass(), secondJobProperties.getCron(),
                        secondJobProperties.getShardingTotalCount(), secondJobProperties.getShardingItemParameters()),
                jobEventConfiguration);
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron,
            final int shardingTotalCount, final String shardingItemParameters) {
        JobCoreConfiguration.Builder builder = JobCoreConfiguration
                .newBuilder(jobClass.getName(), cron, shardingTotalCount);
        builder = builder.shardingItemParameters(shardingItemParameters);
        builder = builder.failover(true);
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(builder.build(), jobClass.getCanonicalName()))
                .overwrite(true).build();
    }
}
