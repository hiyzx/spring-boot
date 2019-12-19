package org.zero.enums;

import io.swagger.annotations.ApiModelProperty;

/**
 * 状态码
 *
 * @author yezhaoxing
 * @date 2017/4/29
 */
public enum StringEnum {

    @ApiModelProperty("用户未登录")
    NOT_LOGIN("403"),

    @ApiModelProperty("参数不匹配")
    PARAM_NOT_MATCH("400"),

    @ApiModelProperty("404")
    PAGE_NOT_FOUND("404"),

    REQUEST_METHOD_NOT_SUPPORT("405"),

    INTERNAL_SERVER_ERROR("500"),

    SUCCESS("000000"),

    VALID_FAIL("100001"),

    TEST_FAIL("100002");

    private String codeEnum;

    private StringEnum(String value) {
        this.codeEnum = value;
    }

    public String getCodeEnum() {
        return codeEnum;
    }

}
