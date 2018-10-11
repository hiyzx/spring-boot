package org.zero.shiro.dao;

import org.springframework.data.repository.CrudRepository;
import org.zero.shiro.entity.UserInfo;

public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);
}