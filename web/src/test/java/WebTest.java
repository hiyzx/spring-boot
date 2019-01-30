import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zero.WebApplication;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yezhaoxing
 * @since 2018/12/06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
@WebAppConfiguration
public class WebTest {

    @Autowired
    protected WebApplicationContext wac;

    @Test
    public void test() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Map<String, String> param = new HashMap<>();
        String json = new ObjectMapper().writeValueAsString(param);
        mockMvc.perform(MockMvcRequestBuilders.post("/test").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
