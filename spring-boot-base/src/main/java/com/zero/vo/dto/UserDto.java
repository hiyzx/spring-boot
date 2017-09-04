package com.zero.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
@Data
public class UserDto {

    @ApiModelProperty(value = "年龄")
    @Min(value = 18, message = "年龄必须大于18")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty(value = "名字")
    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 8, message = "姓名长度必须大于2且小于8字")
    private String name;

    @ApiModelProperty(value = "手机号")
    //@Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\\\d{8}$", message = "手机号格式错误")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;
}
