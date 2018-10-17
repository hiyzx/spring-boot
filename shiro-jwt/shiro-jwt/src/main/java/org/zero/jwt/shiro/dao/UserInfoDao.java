package org.zero.jwt.shiro.dao;

import org.springframework.data.repository.CrudRepository;
import org.zero.jwt.shiro.entity.UserInfo;

public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);
}