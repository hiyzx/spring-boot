package org.zero.elastic.job.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yezhaoxing
 * @since 2018/11/05
 */
@Component
@Slf4j
public class FirstJob implements SimpleJob {

    /**
     * 实际开发中，有了任务总片数和当前分片项，就可以对任务进行分片执行了
     * 比如 SELECT * FROM user WHERE status = 0 AND MOD(id, shardingTotalCount) = shardingItem
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("Thread ID: {}, 任务总片数: {}, 当前分片项: {}", Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
        log.info("参数: {}", shardingContext.getShardingParameter());
    }
}
