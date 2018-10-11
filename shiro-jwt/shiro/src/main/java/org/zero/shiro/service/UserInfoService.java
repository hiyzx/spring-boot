package org.zero.shiro.service;

import org.springframework.stereotype.Service;
import org.zero.shiro.dao.UserInfoDao;
import org.zero.shiro.entity.UserInfo;

import javax.annotation.Resource;

@Service
public class UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}