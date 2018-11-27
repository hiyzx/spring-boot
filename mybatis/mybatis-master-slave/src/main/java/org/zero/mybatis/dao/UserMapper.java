package org.zero.mybatis.dao;

import org.zero.mybatis.po.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    User selectById();
}