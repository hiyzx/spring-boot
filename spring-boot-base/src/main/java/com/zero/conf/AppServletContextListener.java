package com.zero.conf;

import com.zero.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author yezhaoxing
 * @date 2017/08/15
 */
@WebListener
@Configuration
public class AppServletContextListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(AppServletContextListener.class);
    @Resource
    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 监听服务器启动,做一些初始化操作
        LOG.info("server is start");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // 暂时没有用到
    }
}
