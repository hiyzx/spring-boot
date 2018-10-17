package org.zero.jwt.shiro.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yezhaoxing
 * @since 2018/10/17
 */
@Data
@AllArgsConstructor
public class ReturnVo {

    private String code;

    private String msg;

    public static ReturnVo success() {
        return new ReturnVo("200", "success");
    }

    public static ReturnVo fail(String code, String msg) {
        return new ReturnVo(code, msg);
    }
}
