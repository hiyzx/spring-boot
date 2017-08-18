package com.zero.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.enums.CodeEnum;
import com.zero.vo.BaseReturnVo;
import com.zero.web.exception.BaseException;

/**
 * 全局异常类配置
 *
 * @author yezhaoxing
 * @date 2017/5/17
 */
@Controller
@ControllerAdvice
public class ExceptionController {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(Include.NON_NULL);
    }

    /**
     * 捕获自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ModelAndView resolveException(BaseException e) {
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        mav.setView(view);
        view.setObjectMapper(MAPPER);
        BaseReturnVo returnVO = new BaseReturnVo(e.getCodeEnum(), e.getMsg());
        mav.addObject(returnVO);
        LOG.error(e.getMessage());
        return mav;
    }

    /**
     * 捕获404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView resolveException(NoHandlerFoundException e) {
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        mav.setView(view);
        view.setObjectMapper(MAPPER);
        BaseReturnVo returnVO = new BaseReturnVo(CodeEnum.PAGE_NOT_FOUND, "page not found");
        mav.addObject(returnVO);
        LOG.error(e.getMessage());
        return mav;
    }

    /**
     * 捕获请求参数相关异常
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ModelAndView resolveException(ServletRequestBindingException e) {
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        mav.setView(view);
        view.setObjectMapper(MAPPER);
        BaseReturnVo returnVO = new BaseReturnVo(CodeEnum.PARAM_NOT_MATCH, "param not match");
        mav.addObject(returnVO);
        LOG.error(e.getMessage());
        return mav;
    }

    /**
     * 未被前两个异常捕获,都会被该方法处理
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView resolveException(Exception e) {
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        mav.setView(view);
        view.setObjectMapper(MAPPER);
        BaseReturnVo returnVO = new BaseReturnVo(CodeEnum.INTERNAL_SERVER_ERROR, "Internal Server Error");
        mav.addObject(returnVO);
        LOG.error(e.getMessage(), e);
        return mav;
    }
}
