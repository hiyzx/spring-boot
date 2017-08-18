package com.zero.web.controller;

import com.zero.service.LoginService;
import com.zero.util.SessionHelper;
import com.zero.vo.BaseReturnVo;
import com.zero.vo.ReturnVo;
import com.zero.vo.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yezhaoxing
 * @date 2017/08/18
 */
@RestController
@Api(description = "登录相关接口")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping(value = "/register.json")
    @ApiOperation("注册")
    private ReturnVo<String> register(HttpServletRequest request, @RequestBody UserDto userDto) throws Exception {
        int userId = loginService.add(userDto);
        String sessionId = request.getSession().getId();
        SessionHelper.pushUserId(sessionId, userId);
        return ReturnVo.success(sessionId);
    }

    @PostMapping(value = "/login.json")
    @ApiOperation("登陆")
    private ReturnVo<String> login(HttpServletRequest request, @RequestParam String username,
            @RequestParam String password) throws Exception {
        int userId = loginService.login(username, password);
        String sessionId = request.getSession().getId();
        SessionHelper.pushUserId(sessionId, userId);
        return ReturnVo.success(sessionId);
    }

    @PostMapping(value = "/logout.json")
    @ApiOperation("注销")
    private BaseReturnVo logout(HttpServletRequest request) throws Exception {
        SessionHelper.clearSessionId(request.getSession().getId());
        return BaseReturnVo.success();
    }
}
