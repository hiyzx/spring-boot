package org.zero.mybatis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.mybatis.annotation.Master;
import org.zero.mybatis.annotation.Slave;
import org.zero.mybatis.dao.UserMapper;
import org.zero.mybatis.po.User;

/**
 * 用户相关业务接口实现类
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Master
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Slave
    public User select(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}