package com.zero.service;

import com.zero.constant.PointConstant;
import com.zero.dao.UserMapper;
import com.zero.dao.UserPointMapper;
import com.zero.enums.CodeEnum;
import com.zero.enums.PointTypeEnum;
import com.zero.po.User;
import com.zero.po.UserPoint;
import com.zero.util.DateHelper;
import com.zero.vo.dto.UserDto;
import com.zero.web.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    public int login(String username, String password) throws BaseException {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", username).andEqualTo("password", password);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()) {
            throw new BaseException(CodeEnum.LOGIN_FAIL, "用户名或者密码错误!");
        } else {
            User user = users.get(0);
            Integer userId = user.getId();
            Date now = DateHelper.getCurrentDateTime();
            if (user.getLastLoginTime() == null || !DateHelper.isSameDate(now, user.getLastLoginTime())) {
                userPointService.increasePoint(userId, PointTypeEnum.登录, PointConstant.POINT_LOGIN);
            }
            User tmp = new User();
            tmp.setId(userId);
            tmp.setLastLoginTime(now);
            userMapper.updateByPrimaryKeySelective(tmp);
            LOG.info("userId={} login success", userId);
            return userId;
        }

    }

    @Transactional
    public int add(UserDto userDto) {
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
        return userId;
    }
}
