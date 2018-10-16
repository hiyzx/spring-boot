package org.zero.shiro.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest request, String username, String password) {
        ModelAndView mv = new ModelAndView();
        String exceptionMsg = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            mv.setViewName("index");
            return mv;
        } catch (UnknownAccountException e) {
            exceptionMsg = "用户名不存在";
        } catch (IncorrectCredentialsException e) {
            exceptionMsg = "密码有误";
        } catch (AuthenticationException e) {
            exceptionMsg = "用户无效，请联系管理员";
        }
        mv.setViewName("login");
        mv.addObject("msg", exceptionMsg);
        return mv;
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------没有权限-------");
        return "403";
    }

}