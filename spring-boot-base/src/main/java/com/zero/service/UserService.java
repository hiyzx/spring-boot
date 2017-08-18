package com.zero.service;

import com.zero.constant.PointConstant;
import com.zero.dao.UserCheckCountMapper;
import com.zero.dao.UserMapper;
import com.zero.dao.UserPointMapper;
import com.zero.enums.CodeEnum;
import com.zero.enums.PointTypeEnum;
import com.zero.po.User;
import com.zero.po.UserCheckCount;
import com.zero.po.UserPoint;
import com.zero.util.DateHelper;
import com.zero.util.NumberUtil;
import com.zero.vo.CheckRecordVo;
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
 * @date : 2017/4/17
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserCheckCountService userCheckCountService;
    @Resource
    private UserCheckCountMapper userCheckCountMapper;
    @Resource
    private UserPointService userPointService;
    @Resource
    private UserPointMapper userPointMapper;

    public int login(String username, String password) throws BaseException {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", username).andEqualTo("password", password);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()) {
            throw new BaseException(CodeEnum.LOGIN_FAIL, "用户名或者密码错误!");
        } else {
            Integer userId = users.get(0).getId();
            User tmp = new User();
            tmp.setId(userId);
            tmp.setLastLoginTime(DateHelper.getCurrentDateTime());
            userMapper.updateByPrimaryKeySelective(tmp);
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
        int userId = tmp.getId();// 只能用这种方式获取id
        LOG.info("userId={} name={} phone={} register success", userId, name, userDto.getPassword());
        UserPoint userPoint = new UserPoint();
        userPoint.setUserId(userId);
        userPoint.setPoint(0);
        userPointMapper.insert(userPoint);
        return userId;
    }

    @Transactional
    public void check(Integer userId) throws BaseException {
        UserCheckCount userCheckCount = userCheckCountService.getByUserId(userId);
        Date now = DateHelper.getCurrentDateTime();
        if (userCheckCount == null) {// 第一次签到
            UserCheckCount tmp = new UserCheckCount();
            tmp.setUserId(userId);
            tmp.setCheckTime(now);
            tmp.setContinueCount(1);
            tmp.setMaxCount(1);
            tmp.setSum(1);
            tmp.setHistory(NumberUtil.moveByte(0, 1));
            userCheckCountMapper.insertSelective(tmp);
            LOG.info("userId={} first time check", userId);
            // 签到增加积分
            userPointService.increasePoint(userId, PointTypeEnum.签到, PointConstant.POINT_CHECK);
        } else {// 非第一次签到
            Date checkTime = userCheckCount.getCheckTime();
            boolean sameDate = DateHelper.isSameDate(checkTime, now);
            if (!sameDate) {// 同一天签到不加分
                int daysBetween = DateHelper.daysBetween(checkTime, now);
                UserCheckCount tmp = new UserCheckCount();
                tmp.setId(userCheckCount.getId());
                tmp.setCheckTime(now);
                tmp.setSum(userCheckCount.getSum() + 1);
                tmp.setHistory(NumberUtil.moveByte(userCheckCount.getHistory(), daysBetween));
                if (daysBetween > 1) {
                    tmp.setContinueCount(1);
                } else {
                    int continueCount = userCheckCount.getContinueCount() + 1;
                    tmp.setContinueCount(continueCount);
                    if (continueCount > userCheckCount.getMaxCount()) {
                        tmp.setMaxCount(continueCount);
                    }
                    Integer point = continueCheckScore(continueCount);
                    if (point != null) {
                        userPointService.increasePoint(userId, PointTypeEnum.连续签到, point);
                    }
                }
                userCheckCountMapper.updateByPrimaryKeySelective(tmp);
                LOG.info("userId={} continue check day={}", userId, tmp.getContinueCount());
                // 签到增加积分
                userPointService.increasePoint(userId, PointTypeEnum.签到, PointConstant.POINT_CHECK);
            } else {
                throw new BaseException(CodeEnum.CHECK_REPEAT, "今天已经签到过了!");
            }
        }
    }

    // 连续签到获取积分规则
    private Integer continueCheckScore(int continueCheckCount) {
        Integer rtn;
        if (continueCheckCount == 3) {
            rtn = PointConstant.POINT_CONTINUE_3;
        } else if (continueCheckCount == 7) {
            rtn = PointConstant.POINT_CONTINUE_7;
        } else if (continueCheckCount == 30) {
            rtn = PointConstant.POINT_CONTINUE_30;
        } else {
            rtn = null;
        }
        return rtn;
    }

    public CheckRecordVo queryCheckRecord(Integer userId) {
        UserCheckCount userCheckCount = userCheckCountService.getByUserId(userId);
        CheckRecordVo rtn = new CheckRecordVo();
        if (userCheckCount == null) {
            rtn.setHasCheck(false);
        } else {
            Date now = DateHelper.getCurrentDateTime();
            rtn.setHasCheck(DateHelper.isSameDate(now, userCheckCount.getCheckTime()));
            rtn.setLastCheckTime(userCheckCount.getCheckTime());
            String checkHistory = NumberUtil.toFullBinaryString(userCheckCount.getHistory());
            rtn.setCheckHistory(checkHistory.substring(checkHistory.indexOf("1")));
        }
        return rtn;
    }
}
