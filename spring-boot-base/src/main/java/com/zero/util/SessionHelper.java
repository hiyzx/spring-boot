package com.zero.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author yezhaoxing
 * @since : 2017/4/17
 */
public class SessionHelper {
    private static final Logger LOG = LoggerFactory.getLogger(SessionHelper.class);
    private static final int SESSION_EXPIRED_SECONDS = ((Long) TimeUnit.MINUTES.toSeconds(30)).intValue();

    public static void pushUserId(String sessionId, int userId) throws Exception {
        LOG.debug("push sessionId={}", sessionId);
        RedisHelper.set(sessionIdWrapper(sessionId), String.valueOf(userId), SESSION_EXPIRED_SECONDS);
    }

    public static Integer getUserId(String sessionId) throws Exception {
        Integer rtn = null;
        try {
            String s = RedisHelper.get(sessionIdWrapper(sessionId));
            rtn = Integer.valueOf(s);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return rtn;
    }

    public static void heartBeat(String sessionId) throws Exception {
        Integer userId = getUserId(sessionId);
        if (userId != null) {
            LOG.debug("sessionId={} userId={} heartbeat", sessionId, userId);
        }
    }

    public static void clearSessionId(String sessionId) throws Exception {
        RedisHelper.delete(sessionIdWrapper(sessionId));
        LOG.debug("delete sessionId={}", sessionId);
    }

    private static String sessionIdWrapper(String sessionId) {
        return String.format("login_%s", sessionId);
    }

}
