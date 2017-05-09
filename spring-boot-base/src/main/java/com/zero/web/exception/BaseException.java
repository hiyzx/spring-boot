package com.zero.web.exception;

import com.zero.enums.CodeEnum;

/**
 * @Description:
 * @author: yezhaoxing
 * @date: 2017/4/29
 */
public class BaseException extends Exception {

    private CodeEnum codeEnum;

    private String msg;

    public BaseException(CodeEnum codeEnum, String msg) {
        super(msg);
        this.codeEnum = codeEnum;
        this.msg = msg;
    }

    public CodeEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
