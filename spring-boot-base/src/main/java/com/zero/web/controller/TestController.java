package com.zero.web.controller;

import com.zero.dao.UserMapper;
import com.zero.po.User;
import com.zero.service.UserService;
import com.zero.vo.BaseReturnVo;
import com.zero.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2017/09/09
 */
@RestController
@RequestMapping("/test")
@Api(description = "测试接口")
public class TestController {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @GetMapping(value = "/get.json")
    @ApiOperation("获取用户信息")
    public ReturnVo<User> get(@RequestParam Integer userId) throws Exception {
        return ReturnVo.success(userService.getById(userId));
    }

    @GetMapping(value = "/update.json")
    @ApiOperation("获取用户信息")
    public BaseReturnVo update(@RequestParam Integer userId, @RequestParam String username) throws Exception {
        User user = userService.getById(userId);
        user.setName(username);
        userMapper.updateByPrimaryKeySelective(user);
        return BaseReturnVo.success();
    }
}