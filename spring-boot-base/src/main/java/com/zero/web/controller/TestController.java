package com.zero.web.controller;

import com.zero.activemq.MqUtil;
import com.zero.dao.UserMapper;
import com.zero.po.User;
import com.zero.service.UserService;
import com.zero.util.HttpClient;
import com.zero.util.JsonUtil;
import com.zero.vo.BaseReturnVo;
import com.zero.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @Resource
    private MqUtil mqUtil;
    @Resource
    private HttpClient localHttpClient;

    @GetMapping(value = "/get.json")
    @ApiOperation("获取用户信息")
    public ReturnVo<User> get(@RequestParam Integer userId) {
        return ReturnVo.success(userService.getById(userId));
    }

    @GetMapping(value = "/update.json")
    @ApiOperation("修改用户信息")
    public BaseReturnVo update(@RequestParam Integer userId, @RequestParam String username) {
        User user = userService.getById(userId);
        user.setName(username);
        userMapper.updateByPrimaryKeySelective(user);
        return BaseReturnVo.success();
    }

    @GetMapping(value = "/testMQ.json")
    @ApiOperation("测试mq")
    public BaseReturnVo testMQ(@RequestParam Integer userId) {
        User user = userService.getById(userId);
        mqUtil.sendToMQ(JsonUtil.toJSon(user));
        return BaseReturnVo.success();
    }

    @PostMapping(value = "/test.json")
    public BaseReturnVo test() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("receiver", "1041165785@qq.com");
        params.put("title", "测试");
        params.put("content", "内容");
        localHttpClient.post("/mail/sendMail.json", params);
        return BaseReturnVo.success();
    }
}
