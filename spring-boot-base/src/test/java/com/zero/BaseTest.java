package com.zero;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @Description 单元测试基类
 * @author Super.Li
 * @date Apr 27, 2017
 */
@SpringBootTest
public abstract class BaseTest {
    final String SRC_ASCIIDOC = "src/docs/asciidoc";
    final String TARGET_ASCIIDOC = "target/asciidoc";
    final String TARGET_ASCIIDOC_GENERATED = TARGET_ASCIIDOC + "/generated";
    @Resource
    WebApplicationContext wac;
}
