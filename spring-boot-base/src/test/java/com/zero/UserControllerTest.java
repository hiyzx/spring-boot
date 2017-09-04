package com.zero;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zero.po.User;
import com.zero.service.LoginService;
import com.zero.util.JsonUtil;
import com.zero.util.UserContextHelper;
import com.zero.vo.ReturnVo;
import com.zero.vo.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class UserControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserControllerTest.class);
    private static final String NAME = "zero";
    private static final String PHONE = "188";
    private static final String PASSWORD = "123";
    @Resource
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Resource
    private LoginService loginService;
    @Resource
    private DefaultWebSecurityManager securityManager;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        UserDto tmp = new UserDto();
        tmp.setPhone(PHONE);
        tmp.setName(NAME);
        tmp.setPassword(PASSWORD);
        tmp.setAge(12);
        loginService.add(tmp);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(NAME, PASSWORD);
        subject.login(token);
    }

    @Test
    public void testGetUserInfo() throws Exception {
        Integer userId = UserContextHelper.getUserId();
        String responseString = mockMvc
                .perform(get("/user/getUserInfo.json").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        final TypeReference<ReturnVo<User>> REFERENCE = new TypeReference<ReturnVo<User>>() {
        };
        ReturnVo<User> userReturnVo = JsonUtil.readValue(responseString, REFERENCE);
        Assert.assertEquals(userReturnVo.getData().getName(), NAME);
        Assert.assertEquals(userReturnVo.getData().getId(), userId);
    }
}