package org.zero.elastic.search.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yezhaoxing
 * @date 2019/8/8
 */
@Data
public class Customer {

    private String accountNumber;

    private BigDecimal balance;

    private String firstname;

    private String lastname;

    private Integer age;

    private String gender;

    private String address;

    private String employer;

    private String email;

    private String city;

    private String state;
}
