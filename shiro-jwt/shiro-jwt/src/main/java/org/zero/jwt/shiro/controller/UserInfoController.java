package org.zero.jwt.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.jwt.shiro.vo.ReturnVo;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * 用户查询.
     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public ReturnVo userInfo() {
        return ReturnVo.success();
    }

    /**
     * 用户添加;
     */
    @PostMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public ReturnVo userInfoAdd() {
        return ReturnVo.success();
    }

    /**
     * 用户删除;
     */
    @PostMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public ReturnVo userDel() {
        return ReturnVo.success();
    }
}