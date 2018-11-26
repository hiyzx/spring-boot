package org.zero.socket.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zero.socket.server.socket.SocketServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 通过  @WebListener 或者 使用代码注册  ServletListenerRegistrationBean
 *
 */
@WebListener
@Slf4j
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(servletContextEvent.getServletContext());
        SocketServer socketServer = (SocketServer) context.getBean("socketServer");
        if (null != socketServer) {
            socketServer.startSocketServer();
        } else {
            log.info("未找到 socketServer的bean");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}