package com.zero.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossDomainInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(CrossDomainInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.debug("{}", request.getRequestURL());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Key");
		response.setHeader("access-control-allow-credentials", "true");
		response.setHeader("access-control-allow-methods", "GET,POST,PUT,DELETE,OPTIONS");
		return true;
	}
}
