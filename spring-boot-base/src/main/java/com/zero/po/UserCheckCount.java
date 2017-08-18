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
@Table(name = "user_check_count")
public class UserCheckCount implements Serializable {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "关联的用户id")
    private Integer userId;

    @ApiModelProperty(value = "签到时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT, timezone = TimeZone.TIMEZONE)
    private Date checkTime;

    @ApiModelProperty(value = "连续签到天数")
    private Integer continueCount;

    @ApiModelProperty(value = "最长连续签到天数")
    private Integer maxCount;

    @ApiModelProperty(value = "签到历史记录")
    private Long history;

    @ApiModelProperty(value = "总的签到天数")
    private Integer sum;
}