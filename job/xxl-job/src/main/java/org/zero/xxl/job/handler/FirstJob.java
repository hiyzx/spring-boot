package org.zero.xxl.job.handler;

import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * @author yezhaoxing
 * @date 2019/7/2
 */
@JobHandler(value = "firstJobHandler")
@Component
@Slf4j
public class FirstJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("任务执行:{}", s);
        return ReturnT.SUCCESS;
    }
}
