package hessian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.hessian.client.Application;
import org.zero.hessian.server.domain.WeatherVo;
import org.zero.hessian.server.inf.IWeatherService;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class HessianTest {

    @Resource
    private IWeatherService weatherService;

    @Test
    public void test() {
        WeatherVo weather = weatherService.queryWeatherByCityName("shanghai");
    }
}
