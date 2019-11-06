package org.zero.notice.response;

import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/10/17
 */
@Data
public class FeiGeListResponseVo {

    private Integer count;

    private List<FeiGeListUserInfoVo> list;
}
