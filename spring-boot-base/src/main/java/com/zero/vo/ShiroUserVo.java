package com.zero.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yezhaoxing
 * @date 2017/08/28
 */
@Data
public class ShiroUserVo {

    private Integer id;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "最后登陆时间")
    private Date lastLoginTime;
}
