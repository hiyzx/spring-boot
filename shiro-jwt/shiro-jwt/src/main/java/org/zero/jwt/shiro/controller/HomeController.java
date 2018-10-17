package org.zero.jwt.shiro.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.jwt.shiro.core.JWTUtil;
import org.zero.jwt.shiro.core.PasswordHash;
import org.zero.jwt.shiro.entity.UserInfo;
import org.zero.jwt.shiro.service.UserInfoService;
import org.zero.jwt.shiro.vo.ReturnVo;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class HomeController {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private PasswordHash passwordHash;

    @GetMapping({"/", "/index"})
    public ReturnVo index() {
        return ReturnVo.success();
    }

    @GetMapping("/toLogin")
    public ReturnVo toLogin() {
        return ReturnVo.fail("403", "not login");
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // 查询用户信息
        UserInfo user = userInfoService.findByUsername(username);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("该账号不存在");
        }
        if (Objects.equals(user.getState(), 0)) {
            throw new LockedAccountException("用户无效,用户账号已被禁用");
        }
        if (!user.getPassword().equals(passwordHash.hashByShiro(password, username + user.getSalt()))) {
            throw new AuthenticationException("密码错误");
        }
        return JWTUtil.sign(username, user.getUid());
    }

}