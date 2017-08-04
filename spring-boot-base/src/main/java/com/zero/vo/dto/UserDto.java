package com.zero.vo.dto;

import io.swagger.annotations.*;
import lombok.Data;

/**
 * @author yezhaoxing
 * @since : 2017/4/17
 */
@Data
public class UserDto {

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;
}
