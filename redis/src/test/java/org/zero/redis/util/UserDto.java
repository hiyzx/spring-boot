package org.zero.redis.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@Data
@AllArgsConstructor
@ToString
public class UserDto {

    private String name;

    private String age;
}
