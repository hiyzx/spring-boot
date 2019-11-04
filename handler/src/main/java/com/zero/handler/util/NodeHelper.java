package com.zero.handler.util;

import com.zero.handler.enums.ChildEnum;
import com.zero.handler.service.ParentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yezhaoxing
 * @date 2019/8/26
 */
@Component
public class NodeHelper {

    // 存储每个节点及其对应的bean,如: 1-firstChildService
    private static final Map<String, ParentService> NODE_BEAN_MAP = new ConcurrentHashMap<>();

    // 存储当前节点和下一节点,如: START-1, 1-2, 2-3, 3-END
    private static final Map<String, String> NODE_MAP = new HashMap<>();

    @Value("${node.sequence}")
    private String nodes;

    /**
     * 项目启动,初始化所有节点
     */
    public void initNodes() {
        if (StringUtils.isEmpty(nodes)) {
            throw new RuntimeException("未配置节点");
        }
        // 获取所有的节点bean
        for (ChildEnum childEnum : ChildEnum.values()) {
            NODE_BEAN_MAP.put(childEnum.getCode(),
                SpringContextUtil.getBean(childEnum.getBeanName(), ParentService.class));
        }
        // 将配置的节点封装成NodeDto
        String[] nodeArray = nodes.split(",");
        for (int i = 0; i < nodeArray.length; i++) {
            if (i < nodeArray.length - 1) {
                NODE_MAP.put(nodeArray[i], nodeArray[i + 1]);
            } else {
                NODE_MAP.put(nodeArray[i], null);
            }
        }
    }
}
