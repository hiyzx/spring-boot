package com.zero.service;

import com.zero.dao.UserMapper;
import com.zero.enums.CodeEnum;
import com.zero.po.User;
import com.zero.vo.dto.UserDto;
import com.zero.web.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @since : 2017/4/17
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;

    public int login(String username, String password) throws BaseException {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", username).andEqualTo("password", password);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()) {
            throw new BaseException(CodeEnum.LOGIN_FAIL, "用户名或者密码错误!");
        } else {
            Integer userId = users.get(0).getId();
            LOG.info("userId={} login success", userId);
            return userId;
        }
    }

    public User getUserInfo(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword("******");
        return user;
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
        LOG.info("name={} phone={} register success", name, userDto.getPassword());
        return tmp.getId();
    }
}
