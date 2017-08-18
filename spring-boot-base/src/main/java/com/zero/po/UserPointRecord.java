package com.zero.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero.enums.PointTypeEnum;
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
@Table(name = "user_point_record")
public class UserPointRecord implements Serializable {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "积分类型")
    private PointTypeEnum type;

    @ApiModelProperty(value = "获得的积分")
    private Integer gainPoint;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT, timezone = TimeZone.TIMEZONE)
    private Date createTime;
}