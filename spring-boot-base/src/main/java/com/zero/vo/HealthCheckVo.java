package com.zero.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 健康检查的对象
 * 
 * @author yezhaoxing
 * @since 2017/5/18
 */
@Data
public class HealthCheckVo {

    @ApiModelProperty("服务名")
    private String serviceName;

    @ApiModelProperty("是否正常")
    private boolean isNormal;

    @ApiModelProperty("连接时间")
    private String costTime;

    @ApiModelProperty("备注")
    private String remark;
}
