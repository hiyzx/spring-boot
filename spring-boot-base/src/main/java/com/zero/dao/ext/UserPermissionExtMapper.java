package com.zero.dao.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zero
 * @date 2017/08/17
 */
public interface UserPermissionExtMapper {

    List<String> selectPermissionByUserId(@Param("userName") String userName);
}
