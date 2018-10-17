package org.zero.shiro.core;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    /**
     * 使用shiro的hash方式
     *
     * @param source
     *            源对象
     * @param salt
     *            加密盐
     * @return 加密后的字符
     */
    public String hashByShiro(Object source, Object salt) {
        return new SimpleHash(algorithmName, source, ByteSource.Util.bytes(salt), hashIterations).toHex();
    }
}
