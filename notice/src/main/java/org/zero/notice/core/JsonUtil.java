package org.zero.notice.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author yezhaoxing
 * @date : 2017/4/17
 */
public final class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
    public static ObjectMapper objectMapper;

    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        T rtn;
        if (StringUtils.isEmpty(jsonStr)) {
            rtn = null;
        } else {
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }

            try {
                rtn = objectMapper.readValue(jsonStr, valueType);
            } catch (Exception e) {
                LOG.error(e.getMessage());
                rtn = null;
            }
        }
        return rtn;
    }

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        T rtn;
        if (StringUtils.isEmpty(jsonStr)) {
            rtn = null;
        } else {
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }

            try {
                rtn = objectMapper.readValue(jsonStr, valueTypeRef);
            } catch (Exception e) {
                LOG.error(e.getMessage());
                rtn = null;
            }
        }
        return rtn;
    }

    public static String toJSon(Object object) {
        String rtn;
        if (object == null) {
            rtn = null;
        } else {
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }

            try {
                rtn = objectMapper.writeValueAsString(object);
            } catch (Exception e) {
                LOG.error(e.getMessage());
                rtn = null;
            }
        }
        return rtn;
    }
}