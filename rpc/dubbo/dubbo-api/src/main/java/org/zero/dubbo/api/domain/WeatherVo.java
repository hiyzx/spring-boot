package org.zero.dubbo.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zero
 * @since 2017/08/09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherVo implements Serializable {

    private Long id;

    private String cityName;

    private String temperature;

    private String condition;

    private Date date;

    private BigDecimal salary;
}
