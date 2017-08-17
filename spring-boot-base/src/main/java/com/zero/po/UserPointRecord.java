package com.zero.po;

import com.zero.enums.PointTypeEnum;
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
@Table(name = "user_point_record")
public class UserPointRecord implements Serializable {
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
    private Date createTime;
}