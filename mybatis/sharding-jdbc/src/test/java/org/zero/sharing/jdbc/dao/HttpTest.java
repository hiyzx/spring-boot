package org.zero.sharing.jdbc.dao;

import cn.hutool.http.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author 水寒
 * @date 2020/5/13
 */
@RunWith(JUnit4.class)
public class HttpTest {


    @Test
    public void insertOrder() throws InterruptedException {
        new Thread(()->{
            HttpUtil.get("http://localhost:8081");
        }).start();
        new Thread(()->{
            HttpUtil.get("http://localhost:8082");
        }).start();
        Thread.sleep(10000);
    }

    @Test
    public void selectOrderByIds() {}
}
