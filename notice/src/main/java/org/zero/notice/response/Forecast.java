package org.zero.notice.response;

import lombok.Data;

import java.util.List;

@Data
public class Forecast {
    private String city;

    private String adcode;

    private String province;

    private String reporttime;

    private List<Cast> casts;
}