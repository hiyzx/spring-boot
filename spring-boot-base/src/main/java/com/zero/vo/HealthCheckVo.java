package com.zero.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 健康检查的对象
 * 
 * @author yezhaoxing
 * @since 2017/5/18
 */
public class HealthCheckVo {
    @ApiModelProperty("服务名")
    private String serviceName;
    @ApiModelProperty("是否正常")
    private boolean isNormal;
    @ApiModelProperty("连接时间")
    private String costTime;
    @ApiModelProperty("备注")
    private String remark;

    public HealthCheckVo() {
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isNormal() {
        return this.isNormal;
    }

    public void setNormal(boolean isNormal) {
        this.isNormal = isNormal;
    }

    public String getCostTime() {
        return this.costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
