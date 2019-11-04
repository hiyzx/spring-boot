package com.zero.handler.service.impl;

import com.zero.handler.service.ParentService;
import org.springframework.stereotype.Service;

/**
 * @author yezhaoxing
 * @date 2019/8/22
 */
@Service("firstChildService")
public class FirstChildServiceImpl implements ParentService {

    @Override
    public String execute(String param) {
        return "firstChild " + param;
    }
}
