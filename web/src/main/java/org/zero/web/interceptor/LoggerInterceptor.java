package org.zero.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.zero.util.JsonUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
@Aspect
@Component
@Slf4j
public class LoggerInterceptor {
    @Resource
    private HttpServletRequest request;

    @Pointcut("execution(public * org.zero.web.controller.*.*(..))")
    private void logController() {}

    @AfterReturning(value = "logController()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        try {
            log.info("request={} || response={}", parseRequest(joinPoint), JsonUtil.toJSon(returnValue));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @AfterThrowing(value = "logController()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        try {
            log.info("request={} || exceptionMessage={}", parseRequest(joinPoint), e.getMessage());
        } catch (Exception e1) {
            log.error(e.getMessage(), e);
        }
    }

    private StringBuilder parseRequest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String[] params = ((MethodSignature) joinPoint.getSignature()).getParameterNames();

        StringBuilder sb = new StringBuilder();
        sb.append(request.getRequestURI());
        if (params.length > 0) {
            sb.append(", parameters->[");
            final String template = "%s=%s, ";
            for (int i = 0; i < params.length; i++) {
                sb.append(String.format(template, params[i], JsonUtil.toJSon(args[i])));
            }
            sb.delete(sb.length() - 2, sb.length()).append("]");
        }
        return sb;
    }
}