package com.zero.web.controller;

import com.zero.enums.CodeEnum;
import com.zero.po.User;
import com.zero.service.UserService;
import com.zero.util.SessionHelper;
import com.zero.vo.BaseReturnVo;
import com.zero.vo.CheckRecordVo;
import com.zero.vo.ReturnVo;
import com.zero.web.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @date : 2017/5/17
 */
@RestController
@Api("用户相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/listUserInfo.json")
    @ApiOperation("批量获取用户信息")
    private ReturnVo<List<User>> listUserInfo(@RequestParam String sessionId,
            @ApiParam(value = "用户id", required = true) @RequestParam List<Integer> userIds) throws Exception {
        return ReturnVo.success(userService.getUserInfo(userIds));
    }

    @GetMapping(value = "/getUserInfo.json")
    @ApiOperation("获取用户信息")
    private ReturnVo<User> getUserInfo(@RequestParam String sessionId,
            @ApiParam(value = "用户id", required = true) @RequestParam int userId) throws Exception {
        if (SessionHelper.getUserId(sessionId) == userId) {
            User userInfo = userService.getSelfInfo(userId);
            return ReturnVo.success(userInfo);
        } else {
            throw new BaseException(CodeEnum.PERMISSION_DENIED, "非法sessionId");
        }
    }

    @PostMapping(value = "/check.json")
    @ApiOperation("签到")
    private BaseReturnVo check(@RequestParam String sessionId) throws Exception {
        userService.check(SessionHelper.getUserId(sessionId));
        return BaseReturnVo.success();
    }

    @PostMapping(value = "/queryCheckRecord.json")
    @ApiOperation("查看签到记录")
    private ReturnVo<CheckRecordVo> queryCheckRecord(@RequestParam String sessionId) throws Exception {
        return ReturnVo.success(userService.queryCheckRecord(SessionHelper.getUserId(sessionId)));
    }
}
