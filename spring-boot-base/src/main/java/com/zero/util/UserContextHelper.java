package com.zero.util;

import com.zero.vo.ShiroUserVo;
import org.apache.shiro.SecurityUtils;

/**
 * @author yezhaoxing
 * @date 2017/08/25
 */
public class UserContextHelper {

    /**
     * 获取当前登录用户对象
     */
    public static ShiroUserVo getUser() {
        return (ShiroUserVo) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前登录用户id
     */
    public static Integer getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户名
     */
    public static String getUserName() {
        return getUser().getName();
    }
}
