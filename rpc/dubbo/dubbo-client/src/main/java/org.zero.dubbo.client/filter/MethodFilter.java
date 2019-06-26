package org.zero.dubbo.client.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @author: yezhaoxing
 * @date: 2017/6/20
 */
public class MethodFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(MethodFilter.class);


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        LOG.info("访问 {} 方法", invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
