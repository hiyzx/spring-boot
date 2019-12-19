package org.zero.vo;

import io.swagger.annotations.ApiModelProperty;
import org.zero.enums.StringEnum;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
public class ReturnVo<T> extends BaseReturnVo {

    @ApiModelProperty("返回结果")
    private T data;

    public ReturnVo() {
    }

    public ReturnVo(StringEnum codeEnum, String msg) {
        super(codeEnum, msg);
    }

    public static <T> ReturnVo<T> success(T data) {
        ReturnVo<T> returnVo = new ReturnVo<>(StringEnum.SUCCESS, SUCCESS_DEFAULT_DESC);
        returnVo.setData(data);
        return returnVo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
