package org.zero.jwt.shiro.core;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import static org.zero.jwt.shiro.constant.SystemConstants.ALGORITHM_NAME;
import static org.zero.jwt.shiro.constant.SystemConstants.HASH_ITERATIONS;

/**
 * shiro密码加密配置
 */
@Component
public class PasswordHash {

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
        return new SimpleHash(ALGORITHM_NAME, source, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
    }
}
