package com.zero.dao.ext;

import org.apache.ibatis.annotations.Param;

/**
 * @author zero
 * @since 2017/08/17
 */
public interface UserPointExtMapper {

    void increasePoint(@Param("userId") Integer userId, @Param("gainScore") Integer gainScore);
}
