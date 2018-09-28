package org.zero.entertainment.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.entertainment.core.TextRecognitionUtil;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TextRecognitionUtilTest {

    @Autowired
    private TextRecognitionUtil textRecognitionUtil;

    @Test
    public void recognition() {
        // textRecognitionUtil.recognition();
    }
}