package org.zero.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.zero.enums.StringEnum;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
@Data
public class BaseReturnVo {

    final static String SUCCESS_DEFAULT_DESC = "success";
    @ApiModelProperty("返回码")
    private String resCode;
    @ApiModelProperty("返回说明")
    private String resDes;

    public BaseReturnVo(StringEnum codeEnum, String msg) {
        this.resCode = codeEnum.getCodeEnum();
        this.resDes = msg;
    }

    public static BaseReturnVo success() {
        return new BaseReturnVo(StringEnum.SUCCESS, SUCCESS_DEFAULT_DESC);
    }

}
