package com.zero.po;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "user_check_count")
public class UserCheckCount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "关联的用户id")
    private Integer userId;

    @ApiModelProperty(value = "签到时间")
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