package org.zero.spring.mybatis.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.activemq.ActiveMqApplication;
import org.zero.activemq.core.MqUtil;

import java.util.stream.LongStream;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@SpringBootTest(classes = ActiveMqApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MqUtilTest {

	@Autowired
	private MqUtil mqUtil;

	@Test
	public void sendToMQ() {
		LongStream longStream = LongStream.rangeClosed(0, 100);
		longStream.forEach(s -> {
			mqUtil.sendToMQ("hello " + s);
		});
	}
}