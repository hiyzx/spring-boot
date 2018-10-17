package org.zero.jwt.shiro.service;

import org.springframework.stereotype.Service;
import org.zero.jwt.shiro.dao.UserInfoDao;
import org.zero.jwt.shiro.entity.UserInfo;

import javax.annotation.Resource;

@Service
public class UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}