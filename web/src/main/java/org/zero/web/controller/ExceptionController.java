package org.zero.web.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.zero.enums.StringEnum;
import org.zero.vo.BaseReturnVo;
import org.zero.web.exception.BaseException;

import java.util.List;

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
     * 捕获405异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView resolveException(HttpRequestMethodNotSupportedException e) {
        return commonResolve(e, StringEnum.REQUEST_METHOD_NOT_SUPPORT, "request method not support");
    }

    /**
     * 捕获自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ModelAndView resolveException(BaseException e) {
        return commonResolve(e, e.getCodeEnum(), e.getMsg());
    }

    /**
     * 捕获404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView resolveException(NoHandlerFoundException e) {
        return commonResolve(e, StringEnum.PAGE_NOT_FOUND, "page not found");
    }

    /**
     * 捕获请求参数相关异常
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ModelAndView resolveException(ServletRequestBindingException e) {
        return commonResolve(e, StringEnum.PARAM_NOT_MATCH, "param not match");
    }

    /**
     * 捕获表单校验的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView resolveException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getDefaultMessage()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return commonResolve(e, StringEnum.VALID_FAIL, sb.toString());
    }

    /**
     * 未被前面异常捕获,都会被该方法处理
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView resolveException(Exception e) {
        return commonResolve(e, StringEnum.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    private ModelAndView commonResolve(Exception e, StringEnum codeEnum, String msg) {
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        mav.setView(view);
        view.setObjectMapper(MAPPER);
        BaseReturnVo returnVO = new BaseReturnVo(codeEnum, msg);
        mav.addObject(returnVO);
        LOG.error(e.getMessage(), e);
        return mav;
    }
}
