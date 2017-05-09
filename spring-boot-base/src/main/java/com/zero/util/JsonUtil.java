package com.zero.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The class JacksonUtil</br>
 * json字符与对像转换
 * 
 */
public final class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
    public static ObjectMapper objectMapper;

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
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

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
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

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
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