import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.sharding.jdbc.Application;
import org.zero.sharding.jdbc.po.User;
import org.zero.sharding.jdbc.service.UserService;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @since 2018/11/28
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ShardingTest {

    @Resource
    private UserService userService;

    @Test
    public void select() {
        User user = userService.select(1);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1);
        user.setName("hiyzx");
        userService.update(user);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setAge(1);
        user.setName("zero");
        userService.save(user);
    }
}
