package org.zero.sharding.jdbc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.sharding.jdbc.dao.UserMapper;
import org.zero.sharding.jdbc.po.User;

/**
 * 用户相关业务接口实现类
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void save(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User select(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}