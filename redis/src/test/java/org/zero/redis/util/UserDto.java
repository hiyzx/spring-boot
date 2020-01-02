package org.zero.redis.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String name;

    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserDto))
            return false;

        UserDto userDto = (UserDto) o;

        if (getName() != null ? !getName().equals(userDto.getName()) : userDto.getName() != null)
            return false;
        return getAge() != null ? getAge().equals(userDto.getAge()) : userDto.getAge() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        return result;
    }
}
