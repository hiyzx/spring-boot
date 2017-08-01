package com.zero.web.controller;

import com.zero.enums.CodeEnum;
import com.zero.vo.BaseReturnVo;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 404处理类
 *
 * @author yezhaoxing
 * @since : 2017/7/17
 */
@Controller
public class ErrorHandlerController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request) {
        return new BaseReturnVo(CodeEnum.PAGE_NOT_FOUND, "page not found");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
