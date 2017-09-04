package com.zero.enums;

/**
 * 状态码
 *
 * @author yezhaoxing
 * @date 2017/4/29
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

    CHECK_REPEAT("100001"),

    /**
     * 参数校验失败
     */
    VALID_FAIL("100002"),

    /**
     * 权限问题
     */
    PERMISSION_DENIED("100003"),

    /**
     * 没有权限
     */
    UN_AUTHOR("100004"),

    /**
     * 帐号不存在
     */
    ACCOUNT_NOT_EXIST("100005"),

    /**
     * 帐号未激活
     */
    ACCOUNT_NOT_ACTIVATE("100006"),

    /**
     * 密码错误
     */
    PASSWORD_WRONG("100007"),

    /**
     * 密码错误超过5次
     */
    WRONG_PASSWORD_5("100008");

    private String CodeEnum;

    private CodeEnum(String value) {
        this.CodeEnum = value;
    }

    public String getCodeEnum() {
        return CodeEnum;
    }

}
