package org.zero.hessian.client.config;

import com.caucho.hessian.io.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
public class MyAbstractSerializerFactory extends AbstractSerializerFactory {
    
    private Map<String, Serializer> serializerMap = new HashMap<>();
    private Map<String, Deserializer> deserializerMap = new HashMap<>();
    
    public MyAbstractSerializerFactory() {
        serializerMap.put(BigDecimal.class.getName(), new StringValueSerializer());
        deserializerMap.put(BigDecimal.class.getName(), new BigDecimalDeserializer());
    }
    
    @Override
    public Serializer getSerializer(Class cl) {
        return serializerMap.get(cl.getName());
    }
    
    @Override
    public Deserializer getDeserializer(Class cl) {
        return deserializerMap.get(cl.getName());
    }
}