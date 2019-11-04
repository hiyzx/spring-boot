package com.zero.handler.enums;

/**
 * @author yezhaoxing
 * @date 2019/8/22
 */
public enum ChildEnum {

    FIRST("1", "firstChildService"), SECOND("2", "secondChildService");

    private String code;

    private String beanName;

    ChildEnum(String code, String beanName) {
        this.code = code;
        this.beanName = beanName;
    }

    public String getCode() {
        return code;
    }

    public String getBeanName() {
        return beanName;
    }

    public static ChildEnum of(String code) {
        ChildEnum[] values = ChildEnum.values();
        for (ChildEnum value : values) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        return null;
    }
}
