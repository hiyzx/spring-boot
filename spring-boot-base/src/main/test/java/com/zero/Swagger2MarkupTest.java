package com.zero;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

import java.io.File;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 使用方法：<br/>
 * 1)启用asciidoctor-maven-plugin<br/>
 * 2)去掉本类@Ignore注解 <br/>
 * 3)mvn clean test -P dev<br/>
 * 4)本类必须是最后一个执行的unit test，否则examples信息无法生效
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class Swagger2MarkupTest extends BaseTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() throws IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(super.wac).build();
        /**
         * 把汇总配置复制到生成文件夹，为文档生成做准备
         */
        FileUtils.copyDirectory(new File(super.SRC_ASCIIDOC), new File(super.TARGET_ASCIIDOC));
    }

    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {
        this.mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
                .andDo(Swagger2MarkupResultHandler.outputDirectory(super.TARGET_ASCIIDOC_GENERATED).build())
                .andExpect(status().isOk());
    }
}
