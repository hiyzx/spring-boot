package org.zero.notice.response;

import lombok.Data;

@Data
public class Cast {
    private String date;

    private String week;

    private String dayweather;

    private String nightweather;

    private Integer daytemp;

    private Integer nighttemp;

    private String daywind;

    private String nightwind;

    private String daypower;

    private String nightpower;
}