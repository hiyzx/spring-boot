package com.zero.enums;

/**
 * @Description:
 * @author: yezhaoxing
 * @date: 2017/5/9
 */
public enum CodeEnum {

	/**
	 * 用户未登录
	 */
	NOT_LOGIN("403"),

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
