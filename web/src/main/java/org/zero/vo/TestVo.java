package org.zero.vo;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2019/8/27
 */
@Data
public class TestVo {

    private String name;

    private Integer age;

    private String address;

    @Override
    public String toString() {
        return "TestVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
