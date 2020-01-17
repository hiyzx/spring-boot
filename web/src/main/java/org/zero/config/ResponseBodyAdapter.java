package org.zero.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ResponseBodyAdapter implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 获取requestMappingHandlerAdapter的返回结果处理器列表
        List<HandlerMethodReturnValueHandler> returnValueHandlers =
            requestMappingHandlerAdapter.getReturnValueHandlers();

        // 创建新的转换器列表
        List<HttpMessageConverter<?>> converters = new ArrayList<>();

        // 自定义消息转化器
        MvcJsonHttpMessageConverter mvcJsonHttpMessageConverter = new MvcJsonHttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        mvcJsonHttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(mvcJsonHttpMessageConverter);

        // 创建自定义的返回结果处理器
        MvcMessageConverterMethodProcessor converterMethodProcessor =
            new MvcMessageConverterMethodProcessor(converters);

        // 新的返回结果处理器列表
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>();

        // 复制旧的返回结果处理器到新的列表
        for (HandlerMethodReturnValueHandler handler : returnValueHandlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                // 替换Spring自带的RequestResponseBody方法返回结果处理器
                newReturnValueHandlers.add(converterMethodProcessor);
            } else {
                newReturnValueHandlers.add(handler);
            }
        }
        // 为requestMappingHandlerAdapter重新设置新的返回结果处理器列表
        requestMappingHandlerAdapter.setReturnValueHandlers(Collections.unmodifiableList(newReturnValueHandlers));
    }
}