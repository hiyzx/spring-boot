package dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.dubbo.api.domain.WeatherVo;
import org.zero.dubbo.api.inf.IWeatherService;
import org.zero.dubbo.client.DubboClientApplication;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DubboClientApplication.class)
public class DubboTest {

    @Reference
    private IWeatherService weatherService;

    @Test
    public void test() {
        WeatherVo weather = weatherService.queryWeatherByCityName("shanghai");
    }
}
