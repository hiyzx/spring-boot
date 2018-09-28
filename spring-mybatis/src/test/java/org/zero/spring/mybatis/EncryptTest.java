package org.zero.spring.mybatis;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EncryptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encrypt() {
        String encrypt = stringEncryptor.encrypt("yzx362311");
    }
}
