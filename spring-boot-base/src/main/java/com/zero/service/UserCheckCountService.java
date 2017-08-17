package com.zero.service;

import com.zero.dao.UserCheckCountMapper;
import com.zero.po.UserCheckCount;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/08/17
 */
public class UserCheckCountService {

    @Resource
    private UserCheckCountMapper userCheckCountMapper;

    public UserCheckCount getByUserId(Integer userId) {
        Example example = new Example(UserCheckCount.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<UserCheckCount> userCheckCounts = userCheckCountMapper.selectByExample(example);
        return userCheckCounts.isEmpty() ? null : userCheckCounts.get(0);
    }
}
