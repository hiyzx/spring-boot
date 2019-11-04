package com.zero.handler.controller;

import com.zero.handler.enums.ChildEnum;
import com.zero.handler.service.ParentService;
import com.zero.handler.util.SpringContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yezhaoxing
 * @date 2019/8/22
 */
@RestController
public class WebController {

    @GetMapping("/hello")
    public String hello(String child, String param) {
        ChildEnum childEnum = ChildEnum.of(child);
        ParentService parentService = SpringContextUtil.getBean(childEnum.getBeanName(), ParentService.class);
        return parentService.execute(param);
    }
}
