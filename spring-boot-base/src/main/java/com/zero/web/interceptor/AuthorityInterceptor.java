package com.zero.web.interceptor;

import com.zero.enums.CodeEnum;
import com.zero.util.SessionHelper;
import com.zero.web.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yezhaoxing
 * @since : 2017/4/17
 */
@Component
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String sessionId = request.getParameter("sessionId");
        if (StringUtils.hasText(sessionId) && SessionHelper.getUserId(sessionId) != null) {
            return true;
        } else {
            LOG.error("url={} need login", request.getRequestURI());
            throw new BaseException(CodeEnum.NOT_LOGIN, "未登录");
        }
    }
}
