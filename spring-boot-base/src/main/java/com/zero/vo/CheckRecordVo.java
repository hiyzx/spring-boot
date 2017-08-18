package com.zero.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero.util.TimeZone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yezhaoxing
 * @date 2017/08/18
 */
@Data
public class CheckRecordVo {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty("是否已经签到")
    private boolean hasCheck;

    @ApiModelProperty("上次签到时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT, timezone = TimeZone.TIMEZONE)
    private Date lastCheckTime;

    @ApiModelProperty("签到历史")
    private String checkHistory;
}
