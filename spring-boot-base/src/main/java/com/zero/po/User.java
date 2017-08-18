package com.zero.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero.util.TimeZone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Table(name = "user")
public class User implements Serializable {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "最后登陆时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT, timezone = TimeZone.TIMEZONE)
    private Date lastLoginTime;
}