package org.zero.jwt.shiro.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.zero.jwt.shiro.vo.ReturnVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yezhaoxing
 * @since 2018/09/26
 */
@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({Exception.class})
    public ReturnVo exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (ex instanceof UnauthorizedException) {
            return ReturnVo.fail("406", "not permission");
        } else {
            return ReturnVo.fail("500", "fail");
        }

    }
}
