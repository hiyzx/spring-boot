package com.zero.service;

import com.zero.dao.UserPointRecordMapper;
import com.zero.dao.ext.UserPointExtMapper;
import com.zero.enums.PointTypeEnum;
import com.zero.po.UserPointRecord;
import com.zero.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zero
 * @date 2017/08/17
 */
@Service
public class UserPointService {

    private static final Logger LOG = LoggerFactory.getLogger(UserPointService.class);
    @Resource
    private UserPointRecordMapper userPointRecordMapper;
    @Resource
    private UserPointExtMapper userPointExtMapper;

    public void increasePoint(Integer userId, PointTypeEnum type, Integer score) {
        // 减少并发问题
        userPointExtMapper.increasePoint(userId, score);
        UserPointRecord userPointRecord = new UserPointRecord();
        userPointRecord.setUserId(userId);
        userPointRecord.setType(type);
        userPointRecord.setGainPoint(score);
        userPointRecord.setCreateTime(DateHelper.getCurrentDateTime());
        userPointRecordMapper.insertSelective(userPointRecord);
        LOG.info("userId={} increase score={} from type{}", userId, score, type);
    }
}
