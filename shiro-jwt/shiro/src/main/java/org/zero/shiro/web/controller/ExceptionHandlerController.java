package org.zero.shiro.web.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yezhaoxing
 * @since 2018/09/26
 */
@Controller
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({Exception.class})
    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView mv = new ModelAndView();
        if (ex instanceof UnauthorizedException) {
            mv.setViewName("/406");
            return mv;
        } else {
            mv.setViewName("/500");
            return mv;
        }

    }
}
