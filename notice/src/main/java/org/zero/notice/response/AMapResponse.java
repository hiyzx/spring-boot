package org.zero.notice.response;

import lombok.Data;

import java.util.List;

/**
 * @author yezhaoxing
 * @since 2018/10/11
 */
@Data
public class AMapResponse {

    private String status;

    private Integer count;

    private String info;

    private String infocode;

    private List<Forecast> forecasts;
}