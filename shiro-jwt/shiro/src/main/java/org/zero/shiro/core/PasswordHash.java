package org.zero.shiro.core;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * shiro密码加密配置
 */
@Component
public class PasswordHash {
    // 加密方法
    @Value("${passwordHash.algorithmName}")
    private String algorithmName;
    // 加密次数
    @Value("${passwordHash.hashIterations}")
    private int hashIterations;

    private String md5Hex(final String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes(StandardCharsets.UTF_8));
    }

    private String md5Hex(final byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    /**
     * 使用shiro的hash方式
     *
     * @param algorithmName
     *            算法
     * @param source
     *            源对象
     * @param salt
     *            加密盐
     * @param hashIterations
     *            hash次数
     * @return 加密后的字符
     */
    private String hashByShiro(String algorithmName, Object source, Object salt, int hashIterations) {
        return new SimpleHash(algorithmName, source, salt, hashIterations).toHex();
    }

    public String toHex(Object source, Object salt) {
        return hashByShiro(algorithmName, source, salt, hashIterations);
    }
}
