package com.zero.service;

import com.zero.constant.PointConstant;
import com.zero.dao.UserMapper;
import com.zero.dao.UserPointMapper;
import com.zero.enums.PointTypeEnum;
import com.zero.po.User;
import com.zero.po.UserPoint;
import com.zero.util.DateHelper;
import com.zero.vo.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yezhaoxing
 * @date 2017/08/18
 */
@Service
public class LoginService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserPointMapper userPointMapper;
    @Resource
    private UserPointService userPointService;

    public void login(Integer userId, Date lastLoginTime, Date now) {
        if (lastLoginTime == null || !DateHelper.isSameDate(now, lastLoginTime)) {
            userPointService.increasePoint(userId, PointTypeEnum.登录, PointConstant.POINT_LOGIN);
        }
        User tmp = new User();
        tmp.setId(userId);
        tmp.setLastLoginTime(now);
        userMapper.updateByPrimaryKeySelective(tmp);
        LOG.info("userId={} login success", userId);
    }

    @Transactional
    public void add(UserDto userDto) {
        User tmp = new User();
        tmp.setAge(userDto.getAge());
        String name = userDto.getName();
        tmp.setName(name);
        tmp.setPassword(userDto.getPassword());
        String phone = userDto.getPhone();
        tmp.setPhone(phone);
        userMapper.insertSelective(tmp);
        int userId = tmp.getId();// 只能用这种方式获取id
        LOG.info("userId={} name={} phone={} register success", userId, name, userDto.getPassword());
        UserPoint userPoint = new UserPoint();
        userPoint.setUserId(userId);
        userPoint.setPoint(0);
        userPointMapper.insert(userPoint);
    }
}
