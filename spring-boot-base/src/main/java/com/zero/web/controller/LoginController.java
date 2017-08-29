package com.zero.web.controller;

import com.zero.enums.CodeEnum;
import com.zero.service.LoginService;
import com.zero.vo.BaseReturnVo;
import com.zero.vo.ReturnVo;
import com.zero.vo.dto.UserDto;
import com.zero.web.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yezhaoxing
 * @date 2017/08/18
 */
@RestController
@RequestMapping("/auth")
@Api(description = "登录相关接口")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping(value = "/toLogin.json")
    @ApiOperation("去登录")
    public BaseReturnVo toLogin() throws BaseException {
        throw new BaseException(CodeEnum.NOT_LOGIN, "not login");
    }

    @GetMapping(value = "/unAuthor.json")
    @ApiOperation("未认证")
    public BaseReturnVo unAuthor() throws BaseException {
        throw new BaseException(CodeEnum.UN_AUTHOR, "not author");
    }

    @PostMapping(value = "/register.json")
    @ApiOperation("注册")
    public BaseReturnVo register(@RequestBody @Valid UserDto userDto) throws Exception {
        loginService.add(userDto);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getName(), userDto.getPassword());
        subject.login(token);
        return ReturnVo.success();
    }

    @PostMapping(value = "/login.json")
    @ApiOperation("登陆")
    public BaseReturnVo login(@RequestParam String username, @RequestParam String password) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return BaseReturnVo.success();
        } catch (UnknownAccountException e) {
            throw new BaseException(CodeEnum.ACCOUNT_NOT_EXIST, "account not exist");
        } catch (DisabledAccountException e) {
            throw new BaseException(CodeEnum.ACCOUNT_NOT_ACTIVATE, "account not activate");
        } catch (IncorrectCredentialsException e) {
            throw new BaseException(CodeEnum.PASSWORD_WRONG, "password wrong");
        }
    }

    @PostMapping(value = "/logout.json")
    @ApiOperation("注销")
    public BaseReturnVo logout() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseReturnVo.success();
    }
}
