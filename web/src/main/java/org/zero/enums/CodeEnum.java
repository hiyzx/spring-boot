package org.zero.enums;

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

    REQUEST_METHOD_NOT_SUPPORT("405"),

    INTERNAL_SERVER_ERROR("500"),

    SUCCESS("000000"),

    VALID_FAIL("100001"),

    TEST_FAIL("100002");

    private String codeEnum;

    private CodeEnum(String value) {
        this.codeEnum = value;
    }

    public String getCodeEnum() {
        return codeEnum;
    }

}
