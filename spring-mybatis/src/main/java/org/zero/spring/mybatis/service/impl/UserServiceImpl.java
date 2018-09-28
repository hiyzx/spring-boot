package org.zero.spring.mybatis.service.impl;

import org.springframework.stereotype.Service;
import org.zero.spring.mybatis.dao.UserMapper;
import org.zero.spring.mybatis.po.User;
import org.zero.spring.mybatis.service.UserService;

import javax.annotation.Resource;


/**
 * @author zero
 * @date 2018/09/28
 */
@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
