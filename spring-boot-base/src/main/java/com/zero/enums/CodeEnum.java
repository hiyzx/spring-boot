package com.zero.enums;

/**
 * 状态码
 *
 * @author yezhaoxing
 * @since 2017/4/29
 */
public enum CodeEnum {

    /**
     * 用户未登录
     */
    NOT_LOGIN("403"),

    PARAM_NOT_MATCH("400"),

    PAGE_NOT_FOUND("404"),

    INTERNAL_SERVER_ERROR("500"),

    SUCCESS("000000"),

    /**
     * 权限问题
     */
    PERMISSION_DENIED("100002"),

    /**
     * 登陆失败
     */
    LOGIN_FAIL("100001");

    private CodeEnum(String value) {
        this.CodeEnum = value;
    }

    private String CodeEnum;

    public String getCodeEnum() {
        return CodeEnum;
    }

}
