package com.zero.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since : 2017/4/17
 */
public class SessionHelper {
    private static final Logger LOG = LoggerFactory.getLogger(SessionHelper.class);
    private static final Map<String, Integer> SESSION_MAP = new HashMap<>();

    public static void pushUserId(String sessionId, int userId) throws Exception {
        LOG.debug("push sessionId={}", sessionId);
        SESSION_MAP.put(sessionId, userId);
    }

    public static Integer getUserId(String sessionId) throws Exception {
        return SESSION_MAP.get(sessionId);
    }

    public static void heartBeat(String sessionId) throws Exception {
        Integer userId = getUserId(sessionId);
        if (userId != null) {
            LOG.debug("sessionId={} userId={} heartbeat", sessionId, userId);
        }
    }

    public static void clearSessionId(String sessionId) throws Exception {
        SESSION_MAP.remove(sessionId);
        LOG.debug("delete sessionId={}", sessionId);
    }
}
